package com.lec.spring.repository;

import com.lec.spring.domain.shop.RecentItem;
import com.lec.spring.domain.shop.Result;

import java.util.List;

public interface RecentItemRepo {
    // 클릭시 최근 목록에 담김
    int insert(Integer userId, String goodNo);

    // 담겨있는지 비교하기위해 갯수 확인
    int selectOne(Integer userId, String goodsNo);

    // 유저의 최근 목록 불러오기
    List<RecentItem> selectAll(Integer userId);

    int delete(Integer userId, String goodsNo);
}
