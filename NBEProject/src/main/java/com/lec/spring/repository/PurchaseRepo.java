package com.lec.spring.repository;

import com.lec.spring.domain.shop.Purchase;
import lombok.Data;

import java.util.List;

import java.util.List;

public interface PurchaseRepo {
    // 주문하기
    int insert(Purchase purchase);
    // 주문내용 수정
    int update(Purchase purchase);


    // 주문 내역 카운트
    Long cntOrder();

    // 전체 주문 내역 확인
    public List<Purchase> listOrder();

    // 사용자의 이름으로 확인
    public List<Purchase> username(String name);


    // 페이지 네이션 기능
    List<Purchase> pagination(int offset, int limit);

    Long cntPurchaseItem();

    // 주문내용 찾기
    List<Purchase> selectById(Integer userId, Integer purchaseItem);
}
