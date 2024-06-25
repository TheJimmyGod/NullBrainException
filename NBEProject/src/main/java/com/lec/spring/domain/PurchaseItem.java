package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItem {
    private Integer id;
    private Integer goodsId;  // 데이터베이스와 일치하도록 String 타입으로 변경   // 만약에 pay.html 오류나면 다시 String으로 바꿔보기
    private Integer count;  // 구매 수량
    private Integer price;
    private String phone;
    private String goodsName;
    private String goodsImage;
    private Integer userId;

}
