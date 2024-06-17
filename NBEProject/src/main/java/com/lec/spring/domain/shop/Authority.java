package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authority {
    private Integer id;
    private String auth;
}
