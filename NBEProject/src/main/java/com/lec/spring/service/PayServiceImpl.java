package com.lec.spring.service;

import com.lec.spring.domain.CartPurchaseItem;
import com.lec.spring.domain.Purchase;
import com.lec.spring.domain.PurchaseItem;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository payRepository;

//    @Override
//    public List<CartPurchaseItem> getSelectedCartItems(List<Integer> selectedItems) {
//        return payRepository.getSelectedCartItems(selectedItems);
//    }

    @Override
    public User getUserInfo(Integer id) {
        return payRepository.selectById(id);
    }

    @Override
    public List<Purchase> listPurchases() {
        return payRepository.findAllPurchase();
    }

    @Override
    public List<PurchaseItem> listPurchaseItems() {
        return payRepository.findAllPurchaseItems();
    }




}
