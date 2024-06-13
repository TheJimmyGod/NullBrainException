package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String content;
    private int like;
    private LocalDateTime regdate;

    //@ToString.Exclude
    //@Builder.Default // builder 제공 안 함
    // Attachment image;

    // private User user;
}