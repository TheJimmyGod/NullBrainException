package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Purchase_item {
    private Integer id;
    private Integer goods_id;
    private Integer amount;
}
