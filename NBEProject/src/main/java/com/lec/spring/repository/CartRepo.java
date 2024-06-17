package com.lec.spring.repository;

import com.lec.spring.domain.shop.Cart;

public interface CartRepo {
    // 장바구니에 담기
    int insert(Cart cart);
    // 장바구니 수정
    int update(Cart cart);
    // 장바구니에서 제거
    int delete(int user_id, int item_id);
}
