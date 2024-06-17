package com.lec.spring.repository;

import com.lec.spring.domain.shop.RequestImage;

import java.util.List;
import java.util.Map;

public interface RequestImageRepo {
    // 특정 요청사항에 이미지들 추가
    int insert(List<Map<String, Object>> list, int purchase_id, int user_id);


    // 특정 요청사항에 이미지 추가
    int save(RequestImage file);

    // 특정 요청사항 의 이미지들 SELECT
    List<RequestImage> findByRequest(int post_id);

    // 특정 이미지(id) 한개 select
    RequestImage findById(int id);

    // 선택된 이미지들 SELECT
    List<RequestImage> findByIds(int [] ids);

    // 선택된 이미지들 DELETE
    int deleteByIds(int[] ids);

    // 특정 이미지를 DB에서 삭제
    int delete(RequestImage file);
}
