package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private Integer id;
    private Integer user_id;
    private Boolean isDefault;
    private String street_addr;
    private String detail_addr;
    private String name;

}
