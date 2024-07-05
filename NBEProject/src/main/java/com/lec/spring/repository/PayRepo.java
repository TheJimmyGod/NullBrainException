package com.lec.spring.repository;


import com.lec.spring.domain.shop.Pay;
import com.lec.spring.dto.PayStatus;

import java.util.List;

public interface PayRepo {
    int insert(Pay pay);
    int update(Pay pay);

    int cancel(String imp_uid, PayStatus payStatus);

    Pay findById(Integer id);

    void updateStatus(Pay pay);

}
