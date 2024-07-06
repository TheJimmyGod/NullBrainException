package com.lec.spring.repository;




import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;

import java.util.List;

public interface CartRepo {
    // 옵션과 결합된 아이템
    int insert(Cart cart);
    List<Cart> findUserCart(int userId);

    List<Cart> getItemList(Integer[] ids);
    Cart getItem(int userId, String goodsNo);

    int deleteItemList(Integer[] ids);

    int deleteItemByGoodsNo(String goodsId);

    int deleteItem(int id);

    int deleteByPay(int payId);

    int update(Cart cart);

    public Address selectByDefaultAdd(Integer user_id);

}
