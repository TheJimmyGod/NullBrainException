package com.lec.spring.service;

import com.lec.spring.domain.shop.Authority;
import com.lec.spring.repository.AuthorityRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepo authorityRepo;
    private UserRepo userRepo;

    @Autowired
    public AuthorityServiceImpl(SqlSession sqlSession){
        authorityRepo = sqlSession.getMapper(AuthorityRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
    }


    @Override
    public int addAuthority(Authority authority) {
        return authorityRepo.insert(authority);
    }

    @Override
    public int deleteAuthority(int id) {
        return authorityRepo.delete(id);
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityRepo.findByName(name);
    }



    @Override
    public int addAuthorityToUser(int userId, int authorityId) {
        return authorityRepo.addAuthority(userId, authorityId);
    }

    @Override
    public int updateAuthority(int userId, int authorityId) {
        return authorityRepo.updateAuthority(userId, authorityId);
    }

//    @Override
//    public int getUserIdByUsername(String username) {
//        return userRepo.selectByUsername(username).get(0).getId();
//    }
}
