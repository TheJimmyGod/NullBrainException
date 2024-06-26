package com.lec.spring.repository;

import com.lec.spring.domain.shop.Opt;

import java.util.List;

public interface OptRepo {
    int insert(String id, String value);

    List<String> selectByGoods(String goods_no);
}
