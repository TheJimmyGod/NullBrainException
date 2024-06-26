package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Authority;
import com.lec.spring.domain.User;
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
    public List<User> findAllUser() {
        return userRepo.selectAll();
    }


    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        return null;
    }

    @Override
    public List<User> pagination(int offset, int limit) {
        return userRepo.pagination(offset, limit);
    }

    @Override
    public void updateGrade(int userId, String grade) {
        userRepo.updateGrade(userId, grade);
    }



    @Override
    public Long cntUser() {
        return userRepo.countUsers();
    }

    @Override
    public User findById(Integer id) {
        return userRepo.selectById(id);
    }

    
    // 유저의 이름으로 검색 회원 다 나오게 하기
    @Override
    public List<User> findAllName(String name) {
        return userRepo.allUser(name);
    }


    // 한승욱 코드
}