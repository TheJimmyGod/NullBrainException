package com.lec.spring.shop.repository;



import com.lec.spring.shop.domain.Cart;

import java.util.List;

public interface CartRepo {
    // 옵션과 결합된 아이템
    int insert(Cart cart);
    List<Cart> findUserCart(int userId);

    List<Cart> getItemList(Integer[] ids);
    Cart getItem(int userId, String goodsNo);

    int deleteItemList(Integer[] ids);

    int deleteItem(int id);

}
