package com.lec.spring.domain.shop;

import com.lec.spring.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Purchase {
    private Integer id;
    private Integer userId;
    private Integer purchaseItem;
    private LocalDateTime regdate;
    private String streetAddress; // 주소
    private String detailAddress;  // 상세주소
    private String phone;
    private String request;

    // 유저의 객체 정보
    private User user;

    // 상품의 정보
    private Goods goods;

    private Integer price;
    private String goodsName;
    private Integer count;
    private String goodsImage;
}
