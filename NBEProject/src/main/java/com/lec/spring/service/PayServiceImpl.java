package com.lec.spring.service;


import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.dto.PaymentRequest;
import com.lec.spring.repository.PayRepo;
import com.lec.spring.repository.PurchaseRepo;
import com.siot.IamportRestClient.IamportClient;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
