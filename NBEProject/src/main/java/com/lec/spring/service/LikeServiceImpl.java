package com.lec.spring.service;

import com.lec.spring.domain.shop.Likes;
import com.lec.spring.repository.LikeRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    private LikeRepo likeRepository;

    public LikeServiceImpl(SqlSession sqlSession) {
        likeRepository = sqlSession.getMapper(LikeRepo.class);
        System.out.println("LikeService() 생성");
    }

    @Override
    public void likePost(Integer post_id, Integer user_id) {
        int count = likeRepository.existsLike(user_id,post_id);
        if(count == 0)
        {
            Likes likes = Likes.builder()
                    .user_id(user_id)
                    .post_id(post_id)
                    .build();

            likeRepository.insertLike(likes);
        }
        else
        {
            likeRepository.deleteLike(user_id, post_id);
        }
    }
}
