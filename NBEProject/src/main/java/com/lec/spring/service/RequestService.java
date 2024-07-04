package com.lec.spring.service;

import com.lec.spring.domain.reviewGoods;
import com.lec.spring.domain.shop.Purchase;

import java.util.List;

public interface RequestService {

    // 특정 유저의 아이템 정보들 갖고오기 + status
    List<reviewGoods> listPurchaseStatus(Integer userId);
}
