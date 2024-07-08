package com.lec.spring.service;

import com.lec.spring.repository.PaymentRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepo paymentRepo;

    @Autowired
    PaymentServiceImpl(SqlSession sqlSession){
        paymentRepo = sqlSession.getMapper(PaymentRepo.class);
    }

    @Override
    public boolean cancelPayment(String paymentUid) {
        try {
            int result = paymentRepo.cancelPayment(paymentUid);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
