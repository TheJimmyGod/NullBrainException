package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.User;
import com.lec.spring.shop.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    @Autowired
    public UserServiceImpl(SqlSession sqlSession) {
        userRepo = sqlSession.getMapper(UserRepo.class);
    }

    @Override
    public User getUser(Integer id) {
        return userRepo.selectById(id);
    }
}
