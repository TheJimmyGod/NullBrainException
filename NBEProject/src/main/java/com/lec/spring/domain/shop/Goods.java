package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class Goods {
    private String productId;
    private String title;
    private String image;
    private String price;
    private String content;
    private String category1;
    private String category2;
    private String category3;
}
