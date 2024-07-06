package com.lec.spring.service;


import com.lec.spring.domain.shop.Pay;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.OrderForm;
import com.lec.spring.dto.PayStatus;
import com.lec.spring.dto.PaymentRequest;
import com.lec.spring.repository.CartRepo;
import com.lec.spring.repository.PayRepo;
import com.lec.spring.repository.PurchaseRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PayServiceImpl implements PayService {

    private final PurchaseRepo purchaseRepo;
    private final PayRepo payRepo;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;



    public PayServiceImpl(SqlSession sqlSession) {
        this.purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        this.payRepo = sqlSession.getMapper(PayRepo.class);
        this.cartRepo = sqlSession.getMapper(CartRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
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
        Pay pay = payRepo.findById(purchaseList.get(0).getPay().getId());
        pay.changePaymentBySuccess(PayStatus.OK, request.getImp_uid());

        payRepo.update(pay);
        cartRepo.deleteByPay(pay.getId());
    }

    @Override
    public Pay findPayById(Integer id) {
        return payRepo.findById(id);
    }

    @Override
    public void updatePayStatus(Integer id, PayStatus status) {
            Pay pay = payRepo.findById(id);
            if(pay != null){
                pay.setStatus(status);
                payRepo.updateStatus(pay);
            }
    }

    @Override
    public void updateByUid(String imp_uid, PayStatus status) {
        payRepo.cancel(imp_uid, status);
    }

}
