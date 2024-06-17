package com.lec.spring.service;

import com.lec.spring.domain.Attachment;
import com.lec.spring.domain.shop.Post;
import com.lec.spring.domain.shop.PostImage;
import com.lec.spring.repository.AttachmentRepo;
import com.lec.spring.repository.PostImageRepo;
import com.lec.spring.repository.PostRepo;
import com.lec.spring.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Autowired
    public PostServiceImpl(SqlSession sqlSession) {
        postRepository = sqlSession.getMapper(PostRepo.class);
        userRepository = sqlSession.getMapper(UserRepo.class);
        postImageRepository = sqlSession.getMapper(PostImageRepo.class);
        System.out.println("PostService() 생성");
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {
        return 0;
    }

    @Override
    public Post detail(Integer id) {
        Post post = postRepository.findById(id);
        if(post != null){
            List<PostImage> imageList = postImageRepository.findByPost(post.getId());
            if(!IsImage(imageList))
                return null;
            post.setImageList(imageList);
        }
        return null;
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
        }
        else {
            fileName += "_" + System.currentTimeMillis();
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

    private boolean IsImage(List<PostImage> imageList){
        String realPath = new File(uploadDir).getAbsolutePath();
        for(PostImage a : imageList){
            BufferedImage imgData = null;
            File f = new File(realPath, a.getFile_name());
            try{
                imgData = ImageIO.read(f);
                if(imgData == null){
                    throw new NullPointerException();
                }else
                    return true;
            }catch (IOException e){
                System.out.println("파일 존재 안함: " + f.getAbsolutePath() + "["+e.getMessage()+"]");
            }catch (NullPointerException e){
                System.out.println("해당 파일은 이미지가 아님: " + f.getAbsolutePath() + "["+e.getMessage()+"]");
            }
        }
        return false;
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> list(Integer page, Model model) {
        if(page == null || page < 1) page = 1;

        HttpSession session = null;
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
        if(cnt > 0){
            if(page > totalPage) page = totalPage;
            int fromRow = (page - 1) * pageRows;
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if(endPage >= totalPage) endPage = totalPage;

            list = postRepository.selectRow(fromRow, pageRows);
            model.addAttribute("list", list);
        }
        else{
            page = 0;
        }

        model.addAttribute("cnt", cnt);
        model.addAttribute("page", page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageRows", pageRows);

        //model.addAttribute("url", U.getRequest().getRequestURI());
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
            if(IsImage(imageList))
            {
                post.setImageList(imageList);
            }
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
}
