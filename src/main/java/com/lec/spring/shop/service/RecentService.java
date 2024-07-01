package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.RecentItem;

import java.util.List;

public interface RecentService {
    int delete(Integer userId, String goodsNo);
    List<RecentItem> getRecentItem(Integer userId);

    int addRecent(Integer userId, String goodsNo);
}
