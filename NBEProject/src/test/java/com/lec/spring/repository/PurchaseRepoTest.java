package com.lec.spring.repository;

import com.lec.spring.domain.shop.Purchase;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PurchaseRepoTest {
    @Autowired
    SqlSession sqlSession;

    PurchaseRepo purchaseRepo;

    @Test
    void selectById() {
//        purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
//        List<Purchase> p = purchaseRepo.selectById(5, 15);
//        if(p != null){
//            System.out.println("success");
//        }else System.out.println("failed");
    }

    @Test
    void findByRequest() {
        purchaseRepo = sqlSession.getMapper(PurchaseRepo.class);
        List<Purchase> p = purchaseRepo.findByRequest("aa1edfbe0d4d4c02b982a7f4a87c5ba7");
        System.out.println(p.size());
    }
}