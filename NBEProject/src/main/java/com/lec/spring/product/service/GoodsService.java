package com.lec.spring.product.service;

import com.lec.spring.domain.Purchase;
import com.lec.spring.domain.PurchaseItem;
import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Opt;
import com.lec.spring.domain.shop.RecentItem;
import com.lec.spring.domain.shop.Review;
import org.springframework.ui.Model;

import java.util.List;

public interface GoodsService {
    void getProds(String category1, String category2, Integer page, Model model);
    Goods getProd(String id);

    void getReviews(String id, Integer page, Model model);


    public int addRecent(Integer userId, String goodsNo);

    public List<Goods> getRecentItem(Integer userId);
}
