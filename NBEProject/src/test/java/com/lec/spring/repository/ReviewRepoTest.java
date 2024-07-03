package com.lec.spring.repository;

import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Review;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReviewRepoTest {

    @Autowired
    SqlSession sqlSession;

    ReviewRepo reviewRepo;
    UserRepo userRepo;
    @Test
    void all() {
//        reviewRepo = sqlSession.getMapper(ReviewRepo.class);
//        List<Review> reviews = reviewRepo.selectReviewByGoods("123",0,100);
//        System.out.println(reviews.size());
    }

    @Test
    void insert() {
//        reviewRepo = sqlSession.getMapper(ReviewRepo.class);
//        User user = userRepo.selectById(1);
//        Review review = Review.builder()
//                .title("def")
//                .content("dffef")
//                .user(user)
//                .goodsId()
//                .rate().build();
    }
}

