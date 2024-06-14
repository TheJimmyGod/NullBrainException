package com.lec.spring.service;

import com.lec.spring.domain.Post;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PostService {
    int write(Post post, Map<String, MultipartFile> files);
    Post detail(Long id);
    List<Post> list();
    List<Post> list(Integer page, Model model);
    int update(Post post, Map<String, MultipartFile> files, Long[] delfile);
    int deleteById(Long id);
}
