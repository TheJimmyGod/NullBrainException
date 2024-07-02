package com.lec.spring.service;


import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.dto.PaymentRequest;
import com.lec.spring.repository.CartRepo;
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
    private final CartRepo cartRepo;

    public PayServiceImpl(SqlSession sqlSession) {
        this.purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        this.payRepo = sqlSession.getMapper(PayRepo.class);
        this.cartRepo = sqlSession.getMapper(CartRepo.class);
    }


    @Override
    public void creatPay(PaymentRequest request){
        List<Purchase> purchaseList = purchaseRepo.findByRequest(request.getMerchant_uid());
        // 해당 주문 정보가 없을 떄
        if(purchaseRepo.findByRequest(request.getMerchant_uid()) == null){
            throw new IllegalArgumentException("해당주문 정보가 없습니다.");
        }

        List<Purchase> purchases = purchaseRepo.findByRequest(request.getMerchant_uid());
        int totalPrice = 0;
        for(Purchase p : purchases){
            totalPrice += p.getAmount() * Integer.parseInt(p.getGoods().getPrice());
            cartRepo.deleteItemByGoodsNo(p.getGoods().getGoodsNo());
        }
//        if(totalPrice != request.getAmount()){
//            throw new IllegalArgumentException("주문 결제금액이 일치하지 않습니다.");
//        }
        Pay pay = payRepo.findById(purchaseList.get(0).getPayId());
        pay.changePaymentBySuccess(PayStatus.OK, request.getImp_uid());


        payRepo.update(pay);
    }

}
