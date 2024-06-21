package com.lec.spring.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private int id;
    private Integer user_id;
    private Boolean isDefault;
    private String street_addr;
    private String detail_addr;
    private String name;

}
