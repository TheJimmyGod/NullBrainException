package com.lec.spring.repository;

import com.lec.spring.domain.shop.Address;

import java.util.List;

public interface AddressRepo {
    // 주소 추가
    int insert(Address address);
    // 주소 수정
    int update(Address address);
    // 주소 삭제
    int delete(int id);
    // 사용자의 모든 주소조회
    List<Address> selectAll(int user_id);
    // 특정 주소 조회
    Address selectById(int id);
}
