package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Cart {
    private Integer user_id;
    private Integer item_id;
    private LocalDateTime regdate;
}
