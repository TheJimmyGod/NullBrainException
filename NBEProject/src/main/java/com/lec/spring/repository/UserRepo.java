package com.lec.spring.repository;

import com.lec.spring.domain.shop.User;
import com.lec.spring.dto.UserDto;

import java.util.List;

public interface UserRepo {
    // 유저 생성
    public int insert(User user);
    // 유저 정보 업데이트
    public int update(User user);
    // 유저 삭제
    public int delete(Integer id);
    // 아이디로 유저 찾기
    public User selectById(Integer id);
    // 유저 이름으로
    public User selectByUsername(String username);
    // 모든 유저확인
    public List<User> selectAll();

    // 유저 카운트
    Long countUsers();

    // 유저 페이지네이션 기능
    List<User> pagination(int offset, int limit);

    // 유저 등급 변경
    void updateGrade(int userId, String grade);

    // 유저의 "이름"으로 전체 조회
    List<User> allUser(String name);
}
