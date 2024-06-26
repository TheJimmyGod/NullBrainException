package com.lec.spring.product.service;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.repository.PurchaseRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepo purchaseRepo;

    public PurchaseServiceImpl(SqlSession sqlSession) {
        this.purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
    }

    @Override
    public List<Purchase> getPurchaseInfo(int userId, int itemId) {
        return purchaseRepo.selectById(userId, itemId);
    }
}
