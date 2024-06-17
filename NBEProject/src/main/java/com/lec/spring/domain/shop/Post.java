package com.lec.spring.domain.shop;

import com.lec.spring.domain.Attachment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Integer id;
    private String content;
    private LocalDateTime regdate;

    @ToString.Exclude
    @Builder.Default // builder 제공 안 함
    private List<Post_image> imageList = new ArrayList<>();

    private User user;
}