package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String content;
    private int like;
    private LocalDateTime regdate;

    @ToString.Exclude
    @Builder.Default // builder 제공 안 함
    private List<Attachment> imageList = new ArrayList<>();

    // private User user;
}