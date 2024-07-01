package com.lec.spring.shop.domain;

import com.lec.spring.shop.dto.OrderGoods;
import com.lec.spring.shop.dto.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private Integer id;
    private String merchantId;
    // Pay 참조키
    private Integer payId;
    private Integer amount;
    private String opt;
    private OrderGoods goods;
    private OrderUser user;
}
