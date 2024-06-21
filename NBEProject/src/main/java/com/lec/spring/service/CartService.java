package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.User;

public interface CartService {

    // 장바구니 서비스

    // 장바구니 추가
    int addCart(Cart cart);


    // 장바구니 삭제
    int removeCart(int user_id, int item_id);

    // 장바구니 특정 user의 특정 상품
    int purchaseItem(int user_id, int item_id, Address address, String street_addr, String detail_address, String phone, String request);
}
