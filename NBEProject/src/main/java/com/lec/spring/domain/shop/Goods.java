package com.lec.spring.domain.shop;

import lombok.Data;

import java.util.List;
@Data
public class Goods{
    List<Item> data;
    Object meta;
}
