package com.lec.spring.repository;

import com.lec.spring.domain.shop.Likes;
import org.apache.ibatis.annotations.Param;

public interface LikeRepo {
    void insertLike(Likes likes);
    int existsLike(@Param("user_id") Integer user_id, @Param("post_id") Integer post_id);
    void deleteLike(@Param("user_id") Integer user_id, @Param("post_id") Integer post_id);
}
