package com.lec.spring.service;

import com.lec.spring.domain.CartPurchaseItem;

import java.util.List;


public interface CartService {


    List<CartPurchaseItem> listPurchaseItems(Integer userId);

    int deleteitem(Integer[] delitem);

    int selectitem(Integer[] selectitem);

}
