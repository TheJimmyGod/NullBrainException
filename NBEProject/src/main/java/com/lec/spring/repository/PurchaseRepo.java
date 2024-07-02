package com.lec.spring.repository;



import com.lec.spring.domain.shop.Purchase;

import java.util.List;

public interface PurchaseRepo {

    int insert(Purchase purchase);
    int delete();
    List<Purchase> findByRequest(String orderUid);

}
