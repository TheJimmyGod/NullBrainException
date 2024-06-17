package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponBox {
    private Integer id;
    private Integer user_id;
    private Integer coupon_id;
    private String code;
    private String expiry;
    private Boolean status;

}
