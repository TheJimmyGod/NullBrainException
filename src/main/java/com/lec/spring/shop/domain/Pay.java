package com.lec.spring.shop.domain;

import com.lec.spring.shop.dto.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pay {
    private Integer id;
    private Integer price;
    private PayStatus status;
    private String paymentUid;

    public Pay(Integer price, PayStatus status){
        this.price = price;
        this.status = status;
    }

    public void changePaymentBySuccess(PayStatus status, String paymentUid){
        this.status = status;
        this.paymentUid = paymentUid;
    }
}
