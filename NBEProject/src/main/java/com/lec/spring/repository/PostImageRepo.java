package com.lec.spring.repository;

import com.lec.spring.domain.shop.PostImage;

import java.util.List;
import java.util.Map;

public interface PostImageRepo {
    int insert(List<Map<String,Object>> list, Integer postId);
    int save(PostImage file);
    List<PostImage> findByPost(Integer postId);
    PostImage findById(Integer id);
    List<PostImage> findByIds(Integer[] ids);
    int deleteByIds(Integer[] ids);
    int delete(PostImage file);
}
