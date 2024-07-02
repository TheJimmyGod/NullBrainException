package com.lec.spring.repository;


import com.lec.spring.domain.shop.Pay;

public interface PayRepo {
    int insert(Pay pay);
    int update(Pay pay);

    Pay findById(Integer id);

    void updateStatus(Pay pay);
}
