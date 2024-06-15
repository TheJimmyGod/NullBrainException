package com.lec.spring.repository;

import com.lec.spring.domain.shop.Goods;

import java.util.List;

public interface GoodsRepo {

    public List<Goods> selectById(String id);
    public List<Goods> selectByCategory(String category1);
    public List<Goods> selectByCategory(String category1, String category2);
    public List<Goods> selectByCategory(String category1, String category2, String category3);
}
