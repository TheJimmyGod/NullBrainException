package com.lec.spring.repository;

import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Result;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GoodsRepoTest {
    @Autowired
    SqlSession sqlSession;
    GoodsRepo goodsRepo;
    @Test
    void test(){
        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        long a = goodsRepo.countAll("여성의류", "니트");
        System.out.println(a);
    }
}