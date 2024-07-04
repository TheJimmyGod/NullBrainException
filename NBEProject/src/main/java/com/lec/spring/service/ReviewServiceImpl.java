package com.lec.spring.service;

import com.lec.spring.domain.reviewGoods;
import com.lec.spring.domain.shop.Review;
import com.lec.spring.repository.ReviewImageRepo;
import com.lec.spring.repository.ReviewRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepo reviewRepository;
    private ReviewImageRepo reviewImageRepository;

    @Autowired
    public ReviewServiceImpl(SqlSession sqlSession) {
        reviewRepository = sqlSession.getMapper(ReviewRepo.class);
        reviewImageRepository = sqlSession.getMapper(ReviewImageRepo.class);
    }

    @Override
    public int writeReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public reviewGoods getPurchaseInfo(Integer id) {
        return reviewRepository.findById(id);
    }

}

