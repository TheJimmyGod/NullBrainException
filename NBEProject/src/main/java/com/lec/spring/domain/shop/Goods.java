package com.lec.spring.domain.shop;

import com.lec.spring.dto.OrderGoods;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goods {
    private String goods_no;
    private String category_code;
    private String name;
    private String keywords;
    private String brand_name;
    private String maker;
    private String price;

    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;
    private String image_5;
    private String image_6;
    private String contents;

    public OrderGoods orderGoods(){
        return OrderGoods.builder()
                .goodsNo(this.goods_no)
                .name(this.name)
                .price(this.price)
                .image(this.image_1)
                .build();
    }
}
