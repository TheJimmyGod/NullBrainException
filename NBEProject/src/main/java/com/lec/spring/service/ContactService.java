package com.lec.spring.service;

import com.lec.spring.domain.shop.Contact;

import java.util.List;

public interface ContactService {
    // 문의사항 입력
    int addContact(Contact contact);

    // 문의사항 수정
    int updateContact(Contact contact);

    // 문의사항 삭제
    int deleteContact(int id);


    // 관리자가 답변한 문의 업데이트
    int updateanswer(Contact contact);

    // Concat 아이디 값으로 조회
    Contact getContactById(int id);

    // USER의 Id 값으로 문의사항 모두 조회
    List<Contact> getContactsByUserId(int userId);

    // 문의사항 모두 조회
    List<Contact> allContacts();

    // 문의글 유저 이름 찾기
    List<Contact> findContactsByUsername(String username);

    Long countAll();

    // 미 답변 카운트 기능
    Long countUnAnswer();

}
