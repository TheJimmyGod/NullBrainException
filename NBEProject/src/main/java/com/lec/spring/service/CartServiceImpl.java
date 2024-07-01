package com.lec.spring.service;



import com.lec.spring.domain.shop.Cart;
import com.lec.spring.repository.CartRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    @Autowired
    public CartServiceImpl(SqlSession sqlSession) {
        cartRepo = sqlSession.getMapper(CartRepo.class);
    }

    @Override
    public List<Cart> listUserItems(Integer userId) {
        return cartRepo.findUserCart(userId);
    }

    @Override
    public List<Cart> selectItems(Integer[] selectitem) {

        List<Cart> itemList = cartRepo.getItemList(selectitem);
        return itemList;
    }

    @Override
    public Cart getByUserIdGoodsId(int userId, String goodsId) {
        return cartRepo.getItem(userId, goodsId);
    }

    @Override
    public int deleteItems(Integer[] delitem) {
        return cartRepo.deleteItemList(delitem);
    }

    @Override
    public int insert(Cart cart) {
        return cartRepo.insert(cart);
    }
}
