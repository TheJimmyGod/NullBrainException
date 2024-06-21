package com.lec.spring.repository;

import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Item;

public interface ItemRepo {
    int insert(Item item);
}
