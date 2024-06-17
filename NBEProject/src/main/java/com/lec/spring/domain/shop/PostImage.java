package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostImage {
    private Integer id;
    private String file_name;
    private Integer post_id;

}
