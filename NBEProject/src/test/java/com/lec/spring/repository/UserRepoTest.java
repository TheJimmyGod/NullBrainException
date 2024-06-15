package com.lec.spring.repository;

import com.lec.spring.domain.shop.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepoTest {
    @Autowired
    SqlSession sqlSession;
    UserRepo userRepo;
    @Test
    void insert() {
        userRepo =  sqlSession.getMapper(UserRepo.class);
        User user = User.builder()
                .username("user1")
                .password("1234")
                .name("cho")
                .phone("0105625")
                .birth("920710")
                .email("cho@naver.com")
                .point(100)
                .build();
        if(userRepo.insert(user) > 0){
            System.out.println("저장성공");
        }
    }

    @Test
    void update() {
        userRepo =  sqlSession.getMapper(UserRepo.class);
        User user = User.builder()
                .id(1)
                .username("user_b")
                .password("1111")
                .phone("1111")
                .email("kim@naver")
                .profileimage("none")
                .grade("Bronze")
                .total_price(100)
                .point(10)
                .build();

        if(userRepo.update(user) > 0){
            System.out.println("업데이트 성공");
        }
    }

    @Test
    void delete() {
        userRepo =  sqlSession.getMapper(UserRepo.class);
        if(userRepo.delete(1) > 0){
            System.out.println("삭제 성공");
        }
    }

    @Test
    void selectById() {
        userRepo =  sqlSession.getMapper(UserRepo.class);

        System.out.println(userRepo.selectById(1));
    }

    @Test
    void selectByUsername() {
        userRepo =  sqlSession.getMapper(UserRepo.class);
        System.out.println(userRepo.selectByUsername("user_b"));
    }

    @Test
    void selectAll() {
        userRepo =  sqlSession.getMapper(UserRepo.class);
    }
}