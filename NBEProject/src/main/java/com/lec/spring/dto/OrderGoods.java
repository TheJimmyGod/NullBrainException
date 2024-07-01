package com.lec.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderGoods {
    private String goodsNo;
    private String name;
    private String price;
    private String image;
}
