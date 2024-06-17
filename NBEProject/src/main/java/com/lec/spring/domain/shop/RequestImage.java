package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestImage {
    private Integer id;
    private String file_name;
    private Integer user_id;
    private Integer item_id;
}
