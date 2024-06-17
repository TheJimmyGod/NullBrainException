package com.lec.spring.repository;

import com.lec.spring.domain.shop.Purchase;

public interface PurchaseRepo {
    // 주문하기
    int insert(Purchase purchase);
    // 주문내용 수정
    int update(Purchase purchase);

}
