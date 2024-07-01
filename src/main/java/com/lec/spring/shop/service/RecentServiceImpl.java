package com.lec.spring.shop.service;

import com.lec.spring.shop.domain.RecentItem;
import com.lec.spring.shop.repository.RecentItemRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecentServiceImpl implements RecentService {
    private final RecentItemRepo recentItemRepo;

    public RecentServiceImpl(SqlSession sqlSession) {
        this.recentItemRepo = sqlSession.getMapper(RecentItemRepo.class);
    }

    @Override
    public int delete(Integer userId, String goodsNo) {
        return recentItemRepo.delete(userId, goodsNo);
    }

    @Override
    public List<RecentItem> getRecentItem(Integer userId) {

        return recentItemRepo.selectAll(userId);
    }

    @Override
    public int addRecent(Integer userId, String goodsNo) {
        return recentItemRepo.insert(userId, goodsNo);
    }


}
