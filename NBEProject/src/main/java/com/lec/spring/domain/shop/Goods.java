package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goods {
    private String productId;
    private String image;
    private String price;
    private String content;
    private String category1;
    private String category2;
    private String category3;
}
