package com.lec.spring.service;


import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Purchase> listPurchaseStatus(Integer userId) {
        return requestRepository.findByUserId(userId);
    }


}
