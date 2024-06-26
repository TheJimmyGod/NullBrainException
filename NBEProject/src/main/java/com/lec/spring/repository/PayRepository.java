package com.lec.spring.repository;

import java.util.List;

import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.domain.Purchase;
import com.lec.spring.domain.PurchaseItem;
import com.lec.spring.domain.User;

public interface PayRepository {

    // 상품 목록
    List<Purchase> findAllPurchase();

    List<PurchaseItem> findAllPurchaseItems();

    // 아이디로 유저 찾기
    public User selectById(Integer id);

//    List<CartPurchaseItem> getSelectedCartItems(List<Integer> selectedItems);
}
