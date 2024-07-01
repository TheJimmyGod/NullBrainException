package com.lec.spring.shop.repository;


import com.lec.spring.shop.domain.Purchase;

import java.util.List;

public interface PurchaseRepo {

    int insert(Purchase purchase);
    int delete();

    List<Purchase> findByRequest(String orderUid);

}
