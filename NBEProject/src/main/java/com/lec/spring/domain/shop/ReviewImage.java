package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewImage {
    private Integer id;
    private Integer review_id;
    private String file_name;
}
