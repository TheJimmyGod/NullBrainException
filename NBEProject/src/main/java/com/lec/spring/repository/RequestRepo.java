package com.lec.spring.repository;

import com.lec.spring.domain.shop.Request;

public interface RequestRepo {
    // 요청 생성
    int insert(Request request);
    // 요청 삭제
    int delete(Request request);
    // 관리자 요청응답
    int update(Request request);
}
