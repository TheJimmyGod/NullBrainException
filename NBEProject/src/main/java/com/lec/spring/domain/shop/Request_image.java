package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request_image {
    private Integer id;
    private String file_name;
    private Integer user_id;
    private Integer purchase_id;
}
