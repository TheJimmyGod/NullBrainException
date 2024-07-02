package com.lec.spring.service;



import com.lec.spring.domain.shop.Cart;

import java.util.List;

public interface CartService {


    List<Cart> listUserItems(Integer userId);

    List<Cart> selectItems(Integer[] selectitem);
    int deleteItems(Integer[] delitem);
    int deleteBYGoodsNo(String goodsId);
    int insert(Cart cart);

    Cart getByUserIdGoodsId(int userId, String goodsId);


}
