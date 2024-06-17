package com.lec.spring.domain.shop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthorities {
    private Integer user_id;
    private Integer authority_id;
}
