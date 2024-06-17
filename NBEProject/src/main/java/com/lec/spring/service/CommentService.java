package com.lec.spring.service;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;

public interface CommentService {
    QryCommentList list(Integer postId);
    QryResult write(Integer postId, Integer userId, String content);
    QryResult delete(Integer id);
}
