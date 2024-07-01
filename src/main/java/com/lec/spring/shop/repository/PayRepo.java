package com.lec.spring.shop.repository;

import com.lec.spring.shop.domain.Pay;

public interface PayRepo {
    int insert(Pay pay);
    int update(Pay pay);

    Pay findById(Integer id);
}
