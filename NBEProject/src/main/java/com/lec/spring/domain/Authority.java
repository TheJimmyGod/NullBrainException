package com.lec.spring.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authority {
    private int id;    // PK
    private String name;    // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
