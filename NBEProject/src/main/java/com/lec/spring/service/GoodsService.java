package com.lec.spring.service;

import com.lec.spring.domain.shop.Goods;
import org.springframework.ui.Model;

public interface GoodsService {
    void getProds(String category1, String category2, Integer page, Model model);
    Goods getProd(String id);

    void getReviews(String id, Integer page, Model model);

}
