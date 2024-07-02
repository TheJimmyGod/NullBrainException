package com.lec.spring.service;

import com.lec.spring.domain.shop.Post;
import com.lec.spring.domain.shop.PostImage;
import com.lec.spring.domain.User;
import com.lec.spring.repository.LikeRepo;
import com.lec.spring.repository.PostImageRepo;
import com.lec.spring.repository.PostRepo;
import com.lec.spring.repository.UserRepo;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Value("${app.upload.path}")
    private String uploadDir;
    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    private PostRepo postRepository;
    private UserRepo userRepository;
    private PostImageRepo postImageRepository;
    private LikeRepo likeRepository;
    @Autowired
    public PostServiceImpl(SqlSession sqlSession) {
        postRepository = sqlSession.getMapper(PostRepo.class);
        userRepository = sqlSession.getMapper(UserRepo.class);
        postImageRepository = sqlSession.getMapper(PostImageRepo.class);
        likeRepository = sqlSession.getMapper(LikeRepo.class);
        System.out.println("PostService() 생성");
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {

        if(files.isEmpty())
        {
            return 0;
        }
        // 현재 로그인 한 작성자 정보
        User user = U.getLoggedUser();
        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다.
        user = userRepository.selectById(user.getId());
        if(user == null)
            return -1;
        post.setUser(user);   // 글 작성자 세팅

        int cnt = postRepository.save(post); // 글 먼저 저장 (그래야 AI된 PK값(id) 받아온다)
        // 첨부파일 추가
        addFiles(files, post.getId());

        return cnt;
    }

    private void addFiles(Map<String, MultipartFile> files, Integer id){
        if(files == null || files.isEmpty()) return;
        for (Map.Entry<String, MultipartFile> e : files.entrySet()){
            if(!e.getKey().startsWith("upfile")) continue;
            PostImage file = upload(e.getValue());
            if(file != null){
                file.setPost_id(id);
                postImageRepository.save(file);
            }
        }
    }

    private PostImage upload(MultipartFile multipartFile){
        PostImage image = null;
        String originalFileName = multipartFile.getOriginalFilename();
        if(originalFileName == null || originalFileName.isEmpty()) return null;
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = sourceName;
        File file = new File(uploadDir, fileName);
        if(file.exists()){
            int pos = fileName.lastIndexOf(".");
            if(pos > -1){
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);
                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            }
            else
            {
                fileName = "_" + System.currentTimeMillis();
            }
        }

        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());
        try{
            Files.copy(multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        image = PostImage.builder().file_name(fileName).build();
        return image;
    }

    @Override
    public List<Post> list() {

        List<Post> list = postRepository.findAll();
        for(int i = 0; i < list.size(); ++i){
            List<PostImage> imageList = postImageRepository.findByPost(list.get(i).getId());
            list.get(i).setImageList(imageList);
        }

        return list;
    }

    @Override
    public List<Post> list(Integer page, Model model) {
        if(page == null || page < 1) page = 1;

        User user = U.getLoggedUser();
        user = userRepository.selectById(user.getId());
        int number = (user == null) ? 1 : user.getId();

        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;
        session.setAttribute("page", page);

        long cnt = postRepository.countAll();
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);

        int startPage = 0;
        int endPage = 0;

        List<Post> list = null;

        List<Post> likedList = postRepository.collectPostsByUser(number);
        List<Map<String, Object>> likes = likeRepository.selectAllLikesCount();

        Map<Integer, Long> likedIndex = new HashMap<>();
        Integer _id = 0;
        Long _count = 0L;
        for (var s : likes)
        {
            for(var z : s.entrySet())
            {
                if(z.getKey().contains("post"))
                {
                    _id = (Integer) z.getValue();
                }
                else
                {
                    _count = (Long) z.getValue();
                }
            }
            likedIndex.put(_id, _count);
        }

        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if(endPage >= totalPage) endPage = totalPage;

            list = postRepository.selectRow(fromRow, pageRows);
            model.addAttribute("list", list);
            model.addAttribute("likedList", likedList);
            model.addAttribute("likedMap", likedIndex);
            for(int i = 0; i < list.size(); ++i){
                List<PostImage> imageList = postImageRepository.findByPost(list.get(i).getId());
                list.get(i).setImageList(imageList);
            }
        }
        else{
            page = 0;
        }

        model.addAttribute("cnt", cnt);
        model.addAttribute("page", page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageRows", pageRows);

        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("writePages", writePages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return list;
    }

    @Override
    public Post selectById(Integer id) {
        Post post = postRepository.findById(id);
        if(post != null){
            List<PostImage> imageList = postImageRepository.findByPost(post.getId());
            post.setImageList(imageList);
        }

        return post;
    }

    @Override
    public int update(Post post, Map<String, MultipartFile> files, Integer[] delfile) {
        int result = 0;
        result = postRepository.update(post);

        addFiles(files, post.getId());

        if(delfile != null){
            for(Integer id : delfile){
                PostImage file = postImageRepository.findById(id);
                if(file != null){
                    delfile(file);
                    postImageRepository.delete(file);
                }
            }
        }

        return result;
    }

    private void delfile(PostImage file){
        String saveDir = new File(uploadDir).getAbsolutePath();
        File f = new File(saveDir, file.getFile_name());
        if(f.exists()){
            if(f.delete()){
                System.out.println("삭제 성공");
            }else {
                System.out.println("삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
    }

    @Override
    public int deleteById(Integer id) {
        int result = 0;
        Post post = postRepository.findById(id);
        if(post != null){
            List<PostImage> imageList = postImageRepository.findByPost(id);
            if(imageList != null && !imageList.isEmpty()){
                for(PostImage f : imageList){
                    delfile(f);
                }
            }
            result = postRepository.delete(post);
        }
        return result;
    }

    @Override
    public Post detail(Integer post_id) {
        return postRepository.findById(post_id);
    }
}
