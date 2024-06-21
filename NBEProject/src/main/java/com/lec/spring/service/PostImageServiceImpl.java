package com.lec.spring.service;

import com.lec.spring.domain.shop.PostImage;
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
    public PostImage findById(Integer id) {
        return postImageRepository.findById(id);
    }
}
