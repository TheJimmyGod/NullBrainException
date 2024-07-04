package com.lec.spring.repository;

import com.lec.spring.domain.shop.PostImage;
import com.lec.spring.domain.shop.ReviewImage;

import java.util.List;
import java.util.Map;

public interface ReviewImageRepo {
    int insert(List<Map<String, Object>> list, int post_id);


    // 특정 문의사항에 이미지 추가
    int save(ReviewImage file);

    // 특정 문의사항 의 이미지들 SELECT
    List<ReviewImage> findByPost(int post_id);

    // 특정 이미지(id) 한개 select
    ReviewImage findById(int id);

    // 선택된 이미지들 SELECT
    List<ReviewImage> findByIds(int [] ids);

    // 선택된 이미지들 DELETE
    int deleteByIds(int[] ids);

    // 특정 이미지를 DB에서 삭제
    int delete(int id);

    // 여기서부터
    // 특정 문의사항에 이미지 추가
    int saveImage(ReviewImage file);
}
