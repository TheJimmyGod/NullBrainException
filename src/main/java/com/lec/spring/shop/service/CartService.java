package com.lec.spring.shop.service;


import com.lec.spring.shop.domain.Cart;

import java.util.List;

public interface CartService {


    List<Cart> listUserItems(Integer userId);

    List<Cart> selectItems(Integer[] selectitem);
    int deleteItems(Integer[] delitem);

    int insert(Cart cart);

    Cart getByUserIdGoodsId(int userId, String goodsId);

}
