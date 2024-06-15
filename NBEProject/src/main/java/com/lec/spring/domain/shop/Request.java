package com.lec.spring.domain.shop;

import lombok.Data;

@Data
public class Request {
    private Integer user_id;
    private Integer purchase_item_id;
    private String type;
    private String result;
}
