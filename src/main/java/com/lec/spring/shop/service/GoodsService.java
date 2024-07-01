package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.Goods;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

public interface GoodsService {
    void getProds(String category1, String category2, Integer page, Model model);
    Goods getProd(String id);

    void getReviews(String id, Integer page, Model model);

}
