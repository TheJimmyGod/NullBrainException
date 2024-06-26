package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseItem {
    private Integer id;
    private Integer amount;
    private Goods goods;
}
