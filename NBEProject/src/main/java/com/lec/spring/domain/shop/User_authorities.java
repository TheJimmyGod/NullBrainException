package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User_authorities {
    private Integer user_id;
    private Integer authority_id;
}
