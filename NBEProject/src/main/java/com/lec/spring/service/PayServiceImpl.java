package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayRepository payRepository;

    @Override
    public User getUserInfo(Integer id) {
        return payRepository.selectById(id);
    }

    @Override
    public Address defaultAddr(Integer user_Id){
        return payRepository.selectByDefaultadd(user_Id);
    }


}
