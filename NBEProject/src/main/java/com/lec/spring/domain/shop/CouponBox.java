package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class CouponBox {
    private Integer id;
    private Integer user_id;
    private Integer coupon_id;
    private String code;
    private String expiry;
    private Boolean status;

}
