package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private Integer user_id;
    private Integer item_id;
    private String type;
    private String result;
}
