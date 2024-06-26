package com.lec.spring.domain.shop;

import lombok.Data;

import java.util.List;
@Data
public class Result {
    List<Goods> data;
    Object meta;
}
