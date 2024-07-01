package com.lec.spring.shop.repository;


import com.lec.spring.shop.domain.User;

import java.util.List;

public interface UserRepo {
    // 유저 생성
    int insert(User user);
    User selectById(Integer id);


    //
}
