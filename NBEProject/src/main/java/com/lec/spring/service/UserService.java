package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.dto.UserDto;
import com.lec.spring.util.U;

import java.util.List;

public interface UserService {

    //username(회원아이디)으로 User 정보 가져오기
    User findByUsername(String username);

    boolean isExist(String username);

    //회원가입
    int register(UserDto userDto);

    int register(User user);


    // 전체 회원 조회
    List<User> findAllUser();

    List<Authority> selectAuthoritiesById(int id);


    // ----------------------------------------------------
    // 한승욱 코드

    // 페이지네이션
    List<User> pagination(int offset, int limit);

    // 유저의 등급 변경 기능
    void updateGrade(int userId, String grade);



    // 회원 수 체크
    Long cntUser();

    User findById(Integer id);

    List<User> findAllName(String name);

    void setStatus(int userId, boolean status);

    // 유저 기본배송정보 가져오기
    Address getDefaultAddr(int userId);

}
