package com.lec.spring.service;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.repository.PurchaseRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepo purchaseRepo;
    private UserRepo userRepo;

    @Autowired
    public PurchaseServiceImpl(SqlSession sqlSession) {
        purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
    }

    // 전체 회원 조회
    @Override
    public List<Purchase> orderList() {
        return purchaseRepo.listOrder();
    }

    @Override
    public Long orderCnt() {
        return purchaseRepo.cntOrder();
    }

    @Override
    public List<Purchase> orderUsername(String name) {
        return purchaseRepo.username(name);
    }

    @Override
    public Long cntPurchaseItem() {
        return purchaseRepo.cntPurchaseItem();
    }

    @Override
    public List<Purchase> pagination(int offset, int limit) {
        return purchaseRepo.pagination(offset, limit);
    }
    @Override
    public List<Purchase> getPurchaseInfo(int userId, int itemId) {
        return purchaseRepo.selectById(userId, itemId);
    }

}
