package com.lec.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    String lastBuildDate;
    Integer total;
    Integer start;
    Integer display;
    List<Item> items;
}


