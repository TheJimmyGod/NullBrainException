package com.lec.spring.repository;

import com.lec.spring.domain.shop.Comment;

import java.util.List;

public interface CommentRepo {
    // 답변 생성
    int insert(Comment comment);
    // 답변 삭제
    int delete(int id);
    // 답변 수정
    int update(Comment comment);
    // 답변 하나 조회
    Comment selectById(int id);
    // 게시물의 모든 답변 조회
    List<Comment> selectByPost(int post_id);
}
