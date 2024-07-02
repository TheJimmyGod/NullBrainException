package com.lec.spring.service;


import com.lec.spring.domain.shop.RecentItem;
import org.springframework.ui.Model;

import java.util.List;

public interface RecentService {
    int delete(Integer userId, String goodsNo);
    void getRecentItem(Integer userId, Integer page, Model model);

    int addRecent(Integer userId, String goodsNo);
}
