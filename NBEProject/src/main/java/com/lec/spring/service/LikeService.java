package com.lec.spring.service;

import org.apache.ibatis.annotations.Param;

public interface LikeService {
    void likePost(Integer post_id, Integer user_id);
}
