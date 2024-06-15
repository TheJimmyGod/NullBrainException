package com.lec.spring.repository;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsRepoTest {
    @Autowired
    SqlSession sqlSession;

    GoodsRepo goodsRepo;
    @Test
    void selectById() {
        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        System.out.println(goodsRepo.selectById("40655140276"));
    }

    @Test
    void selectByCategory() {
        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        System.out.println(goodsRepo.selectById("40655140276"));
    }

    @Test
    void testSelectByCategory() {
        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        System.out.println(goodsRepo.selectById("40655140276"));
    }

    @Test
    void testSelectByCategory1() {
        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        System.out.println(goodsRepo.selectById("40655140276"));
    }
}