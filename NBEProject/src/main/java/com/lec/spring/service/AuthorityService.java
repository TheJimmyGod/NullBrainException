package com.lec.spring.service;

import com.lec.spring.domain.shop.Authority;

public interface AuthorityService {

    // 권한 추가
    int addAuthority(Authority authority);
    // 권한 삭제
    int deleteAuthority(int id);
    // 특정 이름의 권한 정보 읽어오기
    Authority getAuthorityByName(String name);


    // 특정 사용자에 권한 추가
    int addAuthorityToUser(int userId, int authorityId);

    // 특정 사용자(userId)의 권한(authorityId) 업데이트
    int updateAuthority(int userId, int authorityId);

//    int getUserIdByUsername(String username);

}

