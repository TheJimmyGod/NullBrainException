package com.lec.spring.repository;

import com.lec.spring.domain.Post;

import java.util.List;

public interface PostRepository {
    int save(Post post);
    List<Post> postList();
    int update(Post post);
    int delete(Post post);
    List<Post> selectRow(int from, int rows);
    int countAll();
}
