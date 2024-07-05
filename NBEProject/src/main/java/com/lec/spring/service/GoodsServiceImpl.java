package com.lec.spring.service;



import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Review;
import com.lec.spring.repository.*;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepo goodsRepo;
    private final ReviewRepo reviewRepo;
    private final OptRepo optRepo;

    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    @Value("${app.pagination.write-pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page-rows}")
    private int WRITE_ROWS;
    private final int PAGE_ROWS = 5;
    @Autowired
    public GoodsServiceImpl(SqlSession sqlSession){

        goodsRepo = sqlSession.getMapper(GoodsRepo.class);
        reviewRepo = sqlSession.getMapper(ReviewRepo.class);
        optRepo = sqlSession.getMapper(OptRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
        cartRepo = sqlSession.getMapper(CartRepo.class);
    }
    @Override
    public void getProds(String category1, String category2, Integer page, Model model) {

        if(page == null || page < 1)
            page = 1;
        HttpSession session = U.getSession();


        session.setAttribute("page", page);

        long cnt = goodsRepo.countAll(category1, category2);
        int totalPages = (int) Math.ceil(cnt/ (double)15);

        int startPage = 0;
        int endPage = 0;
        List<Goods> list = null;

        if(cnt > 0){
            if(page > totalPages)   page = totalPages;
            int fromRow = (page - 1) * 15;

            startPage = ((page-1) / WRITE_PAGES) * WRITE_PAGES + 1;
            endPage = startPage + WRITE_PAGES -1;
            if(endPage >= totalPages) endPage = totalPages;
            list = goodsRepo.selectByCategory(category1, category2, fromRow, 15);
            model.addAttribute("url", U.getRequest().getRequestURI());
            model.addAttribute("category1", category1);
            model.addAttribute("category2", category2);
            model.addAttribute("list", list);
            model.addAttribute("cnt", cnt);
            model.addAttribute("page", page);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("totalPage", totalPages);
            int cartCnt = cartRepo.findUserCart(U.getLoggedUser().getId()).size();

            model.addAttribute("cartCnt",cartCnt);
        }
//        List<Goods> a = goodsRepo.selectByCategory(category1, category2, WRITE_PAGES, WRITE_ROWS);


    }

    @Override
    public Goods getProd(String id) {
        return goodsRepo.selectById(id);
    }

    // 특정 상품의 리뷰들을 가져온다.
    @Override
    public void getReviews(String good_no, Integer page, Model model) {
        HttpSession session = U.getSession();

        List<String> options = optRepo.selectByGoods(good_no);
        Goods goods = goodsRepo.selectById(good_no);

        // 로그인한 유저
        User user = U.getLoggedUser();
        Address addr = userRepo.getDefaultAddr(user.getId());


        if(page == null || page < 1) page=1;

        session.setAttribute("review_page", page);
        long cnt = reviewRepo.countReview(good_no);
        int totalPages = (int) Math.ceil(cnt/(double)PAGE_ROWS);

        int startPage;
        int endPage;
        long totalRate = 0;
        List<Review> reviews = null;

        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("goods", goods);
        model.addAttribute("reviewCnt", cnt);
        model.addAttribute("options", options);
        model.addAttribute("user", user.oderUser(addr.getStreet_addr(), addr.getDetail_addr()));
        if(cnt > 0){
            if(page > totalPages)   page = totalPages;
            int fromRow = (page - 1) * PAGE_ROWS;

            startPage = ((page-1) / WRITE_PAGES) * WRITE_PAGES + 1;
            endPage = startPage + WRITE_PAGES -1;
            if(endPage >= totalPages) endPage = totalPages;
            reviews = reviewRepo.selectReviewByGoods(good_no, fromRow, PAGE_ROWS);
            for (int i = 0; i < reviews.size(); i++) {
                totalRate += reviews.get(i).getRate();
            }
            totalRate /= reviews.size();
            model.addAttribute("reviews", reviews);
            model.addAttribute("page", page);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("totalPage", totalPages);
            model.addAttribute("totalRate", totalRate);

            int cartCnt = cartRepo.findUserCart(U.getLoggedUser().getId()).size();

            model.addAttribute("cartCnt",cartCnt);

        }
    }




}
