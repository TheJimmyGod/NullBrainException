package com.lec.spring.repository;

import com.lec.spring.domain.shop.Post;

import java.util.List;

public interface PostRepo {
    // 게시글 생성
    int insert(Post post);
    // 게시글 수정
    int update(Post post);
    // 게시글 삭제
    int delete(int id);
    // 나의 게시글 목록보기
    List<Post> showAll(int user_id);
    // 아이디로 게시글 확인
    Post showById(int id);
    // 게시글 좋아요기능
    int like(int user_id, int post_id);
    // 게시글 좋아요 취소
    int cancelLike(int user_id, int post_id);
    // 내가 좋아요한 게시글 보기
    List<Post> showLikedPost(int user_id);
}
