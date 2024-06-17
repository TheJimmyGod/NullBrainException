package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coupon {
    private Integer id;
    private Double rate;
    private String name;
}
