package com.lec.spring.service;


import com.lec.spring.domain.shop.RecentItem;

import java.util.List;

public interface RecentService {
    int delete(Integer userId, String goodsNo);
    List<RecentItem> getRecentItem(Integer userId);

    int addRecent(Integer userId, String goodsNo);
}
