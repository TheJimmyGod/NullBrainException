package com.lec.spring.service;


import com.lec.spring.domain.shop.Pay;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.dto.PaymentRequest;

public interface PayService {
    OrderForm findRequest(String merchantId);
    public void creatPay(PaymentRequest request);

    Pay findPayById(Integer id);
    void updatePayStatus(Integer id, PayStatus status);

}
