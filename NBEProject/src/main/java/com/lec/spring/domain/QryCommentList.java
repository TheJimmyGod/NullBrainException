package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lec.spring.domain.shop.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class QryCommentList extends QryResult {
    @JsonProperty("data")
    List<Comment> list;
}
