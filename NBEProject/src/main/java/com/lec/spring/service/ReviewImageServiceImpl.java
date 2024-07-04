package com.lec.spring.service;

import com.lec.spring.domain.shop.ReviewImage;
import com.lec.spring.repository.ReviewImageRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewImageServiceImpl implements ReviewImageService {

    private ReviewImageRepo reviewImageRepository;

    @Autowired
    public ReviewImageServiceImpl(SqlSession sqlSession) {
        reviewImageRepository = sqlSession.getMapper(ReviewImageRepo.class);
    }

    // 이미지 추가
    @Override
    public int addImage(ReviewImage image) {
        return reviewImageRepository.saveImage(image);
    }

}
