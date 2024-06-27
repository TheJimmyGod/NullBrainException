package com.lec.spring.domain;

import com.lec.spring.domain.shop.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    private Integer id;
    private Integer userId;
    private PurchaseItem itemId;
    private LocalDateTime regdate;
    private String streetAddress; // 주소
    private String detailAddress;  // 상세주소
    private String phone;
    private String request; // 문의사항

    private Integer price;
    private String goodsName;
    private Integer count;
    private String goodsImage;
}
