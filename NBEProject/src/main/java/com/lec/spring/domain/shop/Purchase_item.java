package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class Purchase_item {
    private Integer id;
    private Integer basket_id;
    private Integer purchase_id;
    private Integer goods_id;
    private Integer amount;
}
