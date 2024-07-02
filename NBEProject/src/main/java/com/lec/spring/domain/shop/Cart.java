package com.lec.spring.domain.shop;

import com.lec.spring.dto.OrderGoods;
import com.lec.spring.dto.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    private Integer amount;
    private String opt;
    private OrderGoods goods;
    private OrderUser user;
}
