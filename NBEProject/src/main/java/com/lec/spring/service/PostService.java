package com.lec.spring.service;

import com.lec.spring.domain.shop.Post;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PostService {
    int write(Post post, Map<String, MultipartFile> files);
    List<Post> list();
    List<Post> list(Integer page, Model model);
    Post selectById(Integer id);
    int update(Post post, Map<String, MultipartFile> files, Integer[] delfile);
    int deleteById(Integer id);
    Post detail(Integer post_id);
}
