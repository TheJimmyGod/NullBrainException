package com.lec.spring.service;

import com.lec.spring.domain.shop.User;

import java.util.List;

public interface UserService {

    // username(회원 아이디) 의 User 정보 읽어오기
    User findUserByUsername(String username);

    // 신규 회원 등록
    int registerUser(User user);


}
