package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Likes {
    private Integer user_id;
    private Integer post_id;

}
