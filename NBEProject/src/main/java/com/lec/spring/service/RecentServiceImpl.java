package com.lec.spring.service;


import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.RecentItem;
import com.lec.spring.domain.shop.Review;
import com.lec.spring.repository.GoodsRepo;
import com.lec.spring.repository.RecentItemRepo;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class RecentServiceImpl implements RecentService {
    Integer PAGE_ROWS = 10;
    Integer WRITE_PAGES = 5;
    private final RecentItemRepo recentItemRepo;
    private final GoodsRepo goodsRepo;

    public RecentServiceImpl(SqlSession sqlSession) {
        this.recentItemRepo = sqlSession.getMapper(RecentItemRepo.class);
        this.goodsRepo = sqlSession.getMapper(GoodsRepo.class);
    }

    @Override
    public int delete(Integer userId, String goodsNo) {
        return recentItemRepo.delete(userId, goodsNo);
    }

    @Override
    public void getRecentItem(Integer userId, Integer page, Model model) {

        if(page == null || page < 1)
            page=1;
        HttpSession session = U.getSession();
        session.setAttribute("recent_page", page);
        long cnt = recentItemRepo.count(1);
        int totalPages = (int) Math.ceil(cnt/(double)PAGE_ROWS);

        int startPage;
        int endPage;
        List<RecentItem> recentItems = null;

        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("recentCnt", cnt);
        if(cnt > 0){
            if(page > totalPages)   page = totalPages;
            int fromRow = (page - 1) * PAGE_ROWS;

            startPage = ((page-1) / WRITE_PAGES) * WRITE_PAGES + 1;
            endPage = startPage + WRITE_PAGES -1;
            if(endPage >= totalPages) endPage = totalPages;
            recentItems = recentItemRepo.selectAll(1, fromRow, PAGE_ROWS);
            List<Goods> recentGoods = recentItems.stream().map(e ->
                goodsRepo.selectById(e.getGoods_no())
            ).toList();
            model.addAttribute("items", recentGoods);
            model.addAttribute("page", page);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("totalPage", totalPages);
        }

    }

    @Override
    public int addRecent(Integer userId, String goodsNo) {
        return recentItemRepo.insert(userId, goodsNo);
    }


}
