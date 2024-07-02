package com.lec.spring.repository;

import com.lec.spring.domain.shop.Likes;
import com.lec.spring.domain.shop.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LikeRepo {
    void insertLike(Likes likes);
    List<Map<String, Object>>  selectAllLikesCount();
    int existsLike(@Param("user_id") Integer user_id, @Param("post_id") Integer post_id);
    void deleteLike(@Param("user_id") Integer user_id, @Param("post_id") Integer post_id);
}
