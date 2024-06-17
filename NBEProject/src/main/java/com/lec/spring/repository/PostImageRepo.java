package com.lec.spring.repository;

import com.lec.spring.domain.shop.Post_image;

import java.util.List;
import java.util.Map;

public interface PostImageRepo {
    int insert(List<Map<String,Object>> list, Integer postId);
    int save(Post_image file);
    List<Post_image> findByPost(Integer postId);
    Post_image findById(Integer id);
    List<Post_image> findByIds(Integer[] ids);
    int deleteByIds(Integer[] ids);
    int delete(Post_image file);
}
