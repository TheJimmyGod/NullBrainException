package com.lec.spring.shop.repository;


import com.lec.spring.shop.domain.Goods;

import java.util.List;

public interface GoodsRepo {
    List<Goods> selectByCategory(String category1, String category2, int from, int rows);
    Goods selectById(String id);

    long countAll(String category1, String category2);
    int insert(Goods goods);
}
