package com.lec.spring.service;

import com.lec.spring.domain.Attachment;
import com.lec.spring.domain.Post;
import com.lec.spring.repository.AttachmentRepository;
import com.lec.spring.repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private PostRepository postRepository;
    //private  UserRepository userRepository;
    private AttachmentRepository attachmentRepository;

    @Autowired
    public PostServiceImpl(SqlSession sqlSession) {
        postRepository = sqlSession.getMapper(PostRepository.class);
        //userRepository = sqlSession.getMapper(UserRepository.class);
        attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
        System.out.println("PostService() 생성");
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {
        return 0;
    }

    @Override
    public Post detail(Long id) {
        Post post = postRepository.findById(id);
        if(post != null){
            List<Attachment> imageList = attachmentRepository.findByPost(post.getId());
            if(!IsImage(imageList))
                return null;
            post.setImageList(imageList);
        }
        return null;
    }

    private boolean IsImage(List<Attachment> imageList){
        String realPath = new File(uploadDir).getAbsolutePath();
        for(Attachment a : imageList){
            BufferedImage imgData = null;
            File f = new File(realPath, a.getFilename());
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

        return null;
    }

    @Override
    public int update(Post post, Map<String, MultipartFile> files, Long[] delfile) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
