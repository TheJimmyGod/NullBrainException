package com.lec.spring.domain.shop;

import com.lec.spring.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    private Integer id;
    private Integer user_id;
    private Integer goods_id;

    private LocalDateTime regdate;
    private String title;
    private String content;
    private String status;
    private String answer;

    private User user;
}
