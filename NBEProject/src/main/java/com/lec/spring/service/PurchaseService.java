package com.lec.spring.service;



import com.lec.spring.domain.shop.Cart;
import com.lec.spring.dto.OrderForm;

import java.util.List;

public interface PurchaseService {

    OrderForm createPurchase(List<Cart> items, Integer userId);
    int delete();




}
