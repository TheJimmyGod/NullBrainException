package com.lec.spring.service;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;

public interface CommentService {
    QryCommentList list(Long postId);
    QryResult write(Long postId, Long userId, String content);
    QryResult delete(Long id);
}
