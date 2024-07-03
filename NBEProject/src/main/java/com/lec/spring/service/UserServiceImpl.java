package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.UserAuthorities;
import com.lec.spring.dto.UserDto;
import com.lec.spring.repository.AuthorityRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private  AuthorityRepo authorityRepo;

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

        userDto.setUsername(userDto.getUsername().toUpperCase()); //중복방지
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepo.saveUser(userDto);

        Integer userId = userRepo.getLastInsertedUserId();

        Address address = new Address();
        address.setUser_id(userId);
        address.setStreet_addr(userDto.getStreet_addr());
        address.setDetail_addr(userDto.getDetail_addr());
        address.setName(userDto.getAddressName());
        address.setIsDefault(userDto.getIs_default());

        userRepo.saveAddr(address);

        authorityRepo.addAuthority(userId, 2);

        return 1;
    }

    @Override
    public int register(User user) {
        user.setUsername(user.getUsername().toUpperCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegdate(LocalDateTime.now());

        userRepo.insertOAuth(user);

        Integer userId = userRepo.getLastInsertedUserId();

        authorityRepo.addAuthority(userId, 2);

        return 1;
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.selectAll();
    }


    @Override
    public List<Authority> selectAuthoritiesById(int id) {
        User user = userRepo.selectById(id);
        List<Authority> byUser = authorityRepo.findByUser(user.getId());
        return byUser;
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

    @Override
    public void setStatus(int userId, boolean status) {
        User user = userRepo.selectById(userId);
        if (user != null) {
            if (status) {
                authorityRepo.updateAuthority(userId, 1); // 활성 상태 설정 시 권한 1로 업데이트
                user.setStatus(true);

            } else {
                authorityRepo.updateAuthority(userId, 3); // 비활성 상태로 설정 시 권한 3으로 업데이트
                user.setStatus(false);
            }
            userRepo.updateStatus(userId, user.isStatus()); // DB에 변경
        }
    }
}