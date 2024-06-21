package com.lec.spring.service;

import com.lec.spring.domain.shop.Authority;
import com.lec.spring.domain.shop.User;
import com.lec.spring.dto.UserDto;

import java.util.List;

public interface UserService {

    //username(회원아이디)으로 User 정보 가져오기
    User findByUsername(String username);

    boolean isExist(String username);

    //회원가입
    int register(UserDto userDto);

    List<Authority> selectAuthoritiesById(Long id);
}
