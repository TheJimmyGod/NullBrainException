package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> findByPost(Long post_id);
    int save(Comment comment);
    int deleteById(Long id);
}
