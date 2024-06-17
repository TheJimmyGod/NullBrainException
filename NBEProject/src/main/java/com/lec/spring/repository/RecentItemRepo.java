package com.lec.spring.repository;

import com.lec.spring.domain.shop.Goods;

import java.util.List;

public interface RecentItemRepo {
    // 클릭한 상품목록 확인
    int insert(List<Goods> list);
}
