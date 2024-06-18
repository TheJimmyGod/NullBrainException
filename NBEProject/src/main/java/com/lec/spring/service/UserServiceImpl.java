package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.shop.User;
import com.lec.spring.repository.AuthorityRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private AuthorityRepo authorityRepo;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepo = sqlSession.getMapper(UserRepo.class);

    }




    // 회원가입 시 자동적으로 user 권한 부여
    @Override
    @Transactional
    public int registerUser(User user) {
        user.setName(user.getUsername().toUpperCase());
       int result = userRepo.insert(user);


       // 방금 저장한 userId 가져오기
       int userId = user.getId();
//       result = userRepo.insertUserAuthority(userId, "ROLE_USER");

       return result;
    }

    @Override
    public User findUserByUsername(String username) {

        return userRepo.selectByUsername(username);
    }

}
