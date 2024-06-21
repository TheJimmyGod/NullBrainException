package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Authority;
import com.lec.spring.domain.shop.User;
import com.lec.spring.dto.UserDto;
import com.lec.spring.repository.AuthorityRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private  AuthorityRepo authorityRepo;

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepo=sqlSession.getMapper(UserRepo.class);
        authorityRepo=sqlSession.getMapper(AuthorityRepo.class);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.selectByUsername(username.toUpperCase());
    }

    @Override
    public boolean isExist(String username) {
        User user = userRepo.selectByUsername(username.toUpperCase());

        return user != null;
    }

    @Override
    public int register(UserDto userDto) {

        sqlSession.insert("com.lec.spring.repository.UserRepo.saveUser", userDto);

        Integer userId = sqlSession.selectOne("com.lec.spring.repository.UserRepo.getLastInsertedUserId");

        Address address = new Address();
        address.setUser_id(userId);
        address.setStreet_addr(userDto.getStreet_addr());
        address.setDetail_addr(userDto.getDetail_addr());
        address.setName(userDto.getAddressName());
        address.setIsDefault(userDto.getIs_default());

        sqlSession.insert("com.lec.spring.repository.UserRepo.saveAddr", address);

        return 1;
    }

    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        return null;
    }
}


