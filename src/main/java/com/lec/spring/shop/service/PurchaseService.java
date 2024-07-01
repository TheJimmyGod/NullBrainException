package com.lec.spring.shop.service;


import com.lec.spring.shop.domain.Cart;
import com.lec.spring.shop.dto.OrderForm;

import java.util.List;

public interface PurchaseService {

    OrderForm createPurchase(List<Cart> items, Integer userId);
    int delete();




}
