package com.lec.spring.shop.repository;

import java.util.List;

public interface OptRepo {
    List<String> selectByGoods(String goods_no);
}
