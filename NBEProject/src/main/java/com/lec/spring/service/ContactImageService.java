package com.lec.spring.service;

import com.lec.spring.domain.shop.ContactImage;

import java.util.List;
import java.util.Map;

public interface ContactImageService {

    // 단일 이미지 추가 기능
    int addImage(ContactImage image);

    // 이미지 여러 개 추가
    int addImages(List<Map<String, Object>> images, int contact_id);

    // 이미지의 id 값
    ContactImage getImageById(int id);

    // 여러 이미지의 id 값
    List<ContactImage> getImagesByContactId(int contactId);

    // 이미지 삭제 기능
    int deleteImageById(int id);

    // 여러 이미지 삭제
    int deleteImagesByContactId(int contactId);



}
