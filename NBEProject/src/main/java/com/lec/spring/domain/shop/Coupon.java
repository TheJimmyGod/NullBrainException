package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class Coupon {
    private Integer id;
    private Double rate;
    private String name;
}
