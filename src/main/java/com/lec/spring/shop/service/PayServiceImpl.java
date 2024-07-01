package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.Pay;
import com.lec.spring.shop.domain.Purchase;
import com.lec.spring.shop.dto.OrderForm;
import com.lec.spring.shop.dto.OrderUser;
import com.lec.spring.shop.dto.PayStatus;
import com.lec.spring.shop.dto.PaymentRequest;
import com.lec.spring.shop.repository.PayRepo;
import com.lec.spring.shop.repository.PurchaseRepo;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class PayServiceImpl implements PayService {

    private final PurchaseRepo purchaseRepo;
    private final PayRepo payRepo;

    @Autowired
    private IamportClient iamportClient;

    public PayServiceImpl(SqlSession sqlSession) {
        this.purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        this.payRepo = sqlSession.getMapper(PayRepo.class);
    }

    @Override
    public OrderForm findRequest(String merchantId) {
//        List<Purchase> purchases = purchaseRepo.findByRequest(merchantId);
//        if(purchases == null) throw new IllegalArgumentException("주문 내역이 없습니다.");
//        for(Purchase p : purchases){
//
//        }
//        return new OrderForm(orderUser, merchantId, name.toString(), totalPrice);
        return null;
    }
    @Override
    public void creatPay(PaymentRequest request){
        List<Purchase> purchaseList = purchaseRepo.findByRequest(request.getMerchant_uid());
        // TODO
        // 해당 정보가 없을 떄 처리해주어야한다.

        Pay pay = payRepo.findById(purchaseList.get(0).getPayId());
        pay.changePaymentBySuccess(PayStatus.OK, request.getImp_uid());
    }

}
