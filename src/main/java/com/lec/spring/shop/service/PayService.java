package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.Pay;
import com.lec.spring.shop.dto.OrderForm;
import com.lec.spring.shop.dto.PaymentRequest;

public interface PayService {
    OrderForm findRequest(String merchantId);
    public void creatPay(PaymentRequest request);

}
