package com.lec.spring.domain.shop;

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
}
