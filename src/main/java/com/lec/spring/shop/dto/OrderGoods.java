package com.lec.spring.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
