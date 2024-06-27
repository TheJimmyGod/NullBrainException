package com.lec.spring.repository;

import com.lec.spring.domain.shop.ContactImage;

import java.util.List;
import java.util.Map;

public interface ContactImageRepo {
    // 특정 문의사항에 이미지들 추가
    int insert(List<Map<String, Object>> list, int contact_id);


    // 특정 문의사항에 이미지 추가
    int save(ContactImage file);

    // 특정 문의사항 의 이미지들 SELECT
    List<ContactImage> findByContact(int contact_id);

    // 특정 이미지(id) 한개 select
    ContactImage findById(int id);

    // 선택된 이미지들 SELECT
    List<ContactImage> findByIds(int [] ids);

    // 선택된 이미지들 DELETE
    int deleteByIds(int[] ids);

    // 특정 이미지를 DB에서 삭제
    int delete(ContactImage image);
}
