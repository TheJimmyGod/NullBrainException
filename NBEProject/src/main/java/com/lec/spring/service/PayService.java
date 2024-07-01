package com.lec.spring.service;


import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PaymentRequest;

public interface PayService {
    OrderForm findRequest(String merchantId);
    public void creatPay(PaymentRequest request);

}
