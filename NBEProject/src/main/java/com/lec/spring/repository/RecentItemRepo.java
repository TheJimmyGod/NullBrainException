package com.lec.spring.repository;

import com.lec.spring.domain.shop.RecentItem;
import com.lec.spring.domain.shop.Result;

import java.util.List;

public interface RecentItemRepo {
    // 클릭시 최근 목록에 담김
    int insert(Integer userId, String goodNo);

    int count(Integer userId);

    // 유저의 최근 목록 불러오기
    List<RecentItem> selectAll(Integer userId, Integer from, Integer row);

    int delete(Integer userId, String goodsNo);
}
