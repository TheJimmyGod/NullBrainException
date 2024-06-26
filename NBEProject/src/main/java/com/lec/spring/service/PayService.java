package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.User;

public interface PayService {

    // 특정 유저 정보
    User getUserInfo(Integer id);

    // 기본배송지 정보
    Address defaultAddr(Integer user_Id);




}
