package com.lec.spring.service;

import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<CartPurchaseItem> listPurchaseItems(Integer userId) {
        return cartRepository.findPurchaseItem(userId);
    }

    @Override
    public int deleteitem(Integer[] delitem) {
        List<Integer> ids = Arrays.asList(delitem);
        int result = cartRepository.deletePurchaseItems(ids);
        return result;
    }

    @Override
    public List<CartPurchaseItem> selectitem(Integer[] selectitem){
        List<Integer> ids = Arrays.asList(selectitem);
        List<CartPurchaseItem> cartPurchaseItems = cartRepository.selectPurchaseItems(ids);
        return cartPurchaseItems;
    }

}
