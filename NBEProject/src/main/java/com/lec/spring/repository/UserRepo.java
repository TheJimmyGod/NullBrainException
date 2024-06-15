package com.lec.spring.repository;

import com.lec.spring.domain.shop.User;

import java.util.List;

public interface UserRepo {
    public int insert(User user);
    public int update(User user);
    public int delete(Integer id);
    public User selectById(Integer id);
    public User selectByUsername(String username);
    public List<User> selectAll();
}
