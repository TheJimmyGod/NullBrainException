package com.lec.spring.repository;

import com.lec.spring.domain.shop.PostImage;

import java.util.List;
import java.util.Map;

public interface PostImageRepo {
    // 특정 게시판에 이미지들 추가
    int insert(List<Map<String, Object>> list, int post_id);


    // 특정 게시판에 이미지 추가
    int save(PostImage file);

    // 특정 게시판 의 이미지들 SELECT
    List<PostImage> findByPost(int post_id);

    // 특정 이미지(id) 한개 select
    PostImage findById(int id);

    // 선택된 이미지들 SELECT
    List<PostImage> findByIds(int [] ids);

    // 선택된 이미지들 DELETE
    int deleteByIds(int[] ids);

    // 특정 이미지를 DB에서 삭제
    int delete(PostImage file);
}
