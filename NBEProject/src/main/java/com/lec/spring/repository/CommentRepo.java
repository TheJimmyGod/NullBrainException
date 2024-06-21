package com.lec.spring.repository;

import com.lec.spring.domain.shop.Comment;

import java.util.List;

public interface CommentRepo {
    List<Comment> findByPost(Integer post_id);
    int save(Comment comment);
    int deleteById(Integer id);
}
