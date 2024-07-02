package com.lec.spring.service;



import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PayStatus;

import java.util.List;

public interface PurchaseService {

    OrderForm createPurchase(List<Cart> items, Integer userId);
    int delete();

    // 전체 주문 수 목록
    List<Purchase> orderList();

    // 주문 수 카운트
    Long orderCnt();

    // 사용자의 이름으로 확인하기
    List<Purchase> orderUsername(String name);

    Long cntPurchaseItem();

    List<Purchase> pagination(int offset, int limit);

    void updatePayStatus(Integer purchaseId, PayStatus status);

    Long cntCompleted();
    Long cntPending();
    Long cntFailed();




}
