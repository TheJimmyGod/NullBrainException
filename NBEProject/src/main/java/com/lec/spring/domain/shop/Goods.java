package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Goods {
    private String goods_no;
    private String category_code;
    private String name;
    private String keywords;
    private String brand_name;
    private String maker;
    private List<String> option_titles;
    private List<Opt> option_values;
    private String price;

    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;
    private String image_5;
    private String image_6;
    private String contents;
}
