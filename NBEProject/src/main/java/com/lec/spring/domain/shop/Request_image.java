package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class Request_image {
    private Integer id;
    private String file_name;
    private Integer user_id;
    private Integer purchase_id;
}
