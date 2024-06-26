package com.lec.spring.repository;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.User;

public interface PayRepository {

    // 아이디로 유저 찾기
    public User selectById(Integer id);

    // 기본배송지 찾기
    public Address selectByDefaultadd(Integer user_Id);

}
