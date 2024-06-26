package com.lec.spring.service;

import java.util.List;

import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.domain.Purchase;
import com.lec.spring.domain.PurchaseItem;
import com.lec.spring.domain.User;

public interface PayService {


    User getUserInfo(Integer id);

//    List<CartPurchaseItem> getSelectedCartItems(List<Integer> selectedItems);

    // 구매할 상품 목록 불러오기
    List<Purchase> listPurchases();

    // 구매할 상품 갯수 불러오기
    List<PurchaseItem> listPurchaseItems();

    // 특정 유저 정보



}
