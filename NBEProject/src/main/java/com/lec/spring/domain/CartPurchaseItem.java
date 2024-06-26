package com.lec.spring.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPurchaseItem {
    private Integer id;
    private Integer price;
    private String goodsName;
    private Integer count;
    private String goodsImage;
    private Integer userId;

    @ToString.Exclude
    @Builder.Default    // builder 제공안함
    private List<CartPurchaseItem> itemList = new ArrayList<>();
}
