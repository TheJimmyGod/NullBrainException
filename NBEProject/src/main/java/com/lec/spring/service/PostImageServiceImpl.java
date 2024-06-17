package com.lec.spring.service;

import com.lec.spring.domain.shop.Post_image;
import com.lec.spring.repository.PostImageRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class PostImageServiceImpl implements PostImageService {
    PostImageRepo postImageRepository;

    public PostImageServiceImpl(SqlSession sql) {
        postImageRepository = sql.getMapper(PostImageRepo.class);
    }

    @Override
    public Post_image findById(Integer id) {
        return postImageRepository.findById(id);
    }
}
