package com.lec.spring.repository;

import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Opt;

import java.util.List;
import java.util.Map;

public interface OptRepo {
    int insert(List<Map<String, String>> list, String goods_no);
}
