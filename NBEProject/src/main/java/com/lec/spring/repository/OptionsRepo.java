package com.lec.spring.repository;

import com.lec.spring.domain.shop.Options;

import java.util.List;

public interface OptionsRepo {
    // 옵션 생성
    int insert(Options options);
    // 옵션 제거
    int delete(int id);
    // 제품의 옵션 확인
    List<Options> allOptions(int item_id);
}
