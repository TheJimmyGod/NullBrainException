package com.lec.spring.repository;

import com.lec.spring.domain.shop.Contact;
import com.lec.spring.domain.User;

import java.util.List;

public interface ContactRepo {
    // 문의 사항 작성
    int insert(Contact contact);
    // 문의 사항 삭제
    int delete(int id);
    // 문의 사항 수정
    int update(Contact contact);

    // 유저 이름으로
    List<User> selectByUsername(String username);
    // 문의 사항 아이디로 조회
    Contact showById(int id);
    // 특정 사용자의 문의사항 전체 조회
    List<Contact> showMyContact(int user_id);
    // 관리자의 답변
    int answer(Contact contact);

    int answerupdate(Contact contact);

    List<Contact> findAllContacts();

    // 문의사항 글 갯수 확인
    Long countAllContacts();

    // 문의사항 미답변 확인
    Long countUnanswered();
}
