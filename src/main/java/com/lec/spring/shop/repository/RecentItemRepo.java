package com.lec.spring.shop.repository;

import com.lec.spring.shop.domain.RecentItem;

import java.util.List;

public interface RecentItemRepo {
    // 클릭시 최근 목록에 담김
    int insert(Integer userId, String goodNo);


    // 유저의 최근 목록 불러오기
    List<RecentItem> selectAll(Integer userId);

    int delete(Integer userId, String goodsNo);
}
