package com.lec.spring.product.service;

import com.lec.spring.domain.shop.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> getPurchaseInfo(int userId, int itemId);
}
