package com.lec.spring.service;

import com.lec.spring.domain.shop.Purchase;

import java.util.List;

public interface RequestService {

    // 특정 유저의 물건들의 배송상태
    List<Purchase> listPurchaseStatus(Integer userId);
}
