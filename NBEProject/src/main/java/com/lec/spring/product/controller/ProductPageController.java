package com.lec.spring.product.controller;

import com.lec.spring.domain.shop.PurchaseItem;
import com.lec.spring.product.service.GoodsService;
import com.lec.spring.domain.shop.Goods;
import com.lec.spring.product.service.PurchaseService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/nbe")
public class ProductPageController {

    private final GoodsService goodsService;
    private final PurchaseService purchaseService;

    @Autowired
    public ProductPageController(GoodsService goodsService,
                                 PurchaseService purchaseService){
        this.goodsService = goodsService;
        this.purchaseService = purchaseService;
    }
    // 메인 페이지에서 카테고리 클릭시 제품 리스트 이동
    @GetMapping("/list")
    public String list(String category1, String category2, Integer page, Model model){
        goodsService.getProds(category1, category2, page, model);
        return "/prod/list";
    }
    // 제품 상세 페이지
    @GetMapping("/detail/{good_no}")
    public String detail(@PathVariable String good_no,Integer page, Model model){
        int userId = 5;
        goodsService.getReviews(good_no, page, model);
        String url = U.getRequest().getRequestURI();
        String currentUrl = "/nbe/detail/" + good_no;
        // 최근 상품정보 저장
        if(url.equals(currentUrl)){
            int status = goodsService.addRecent(userId, good_no);
            if(status > 0){
                System.out.println("최근 상품에 추가 완료");
                System.out.println("userId: " + userId + "goods_no: " + good_no);
            }
        }
        return "/prod/detail";
    }

    // 제품 최근 목록
    @GetMapping("/recent")
    public String recent(Model model){
        int userId = 5;
        List<Goods> recentGoods = goodsService.getRecentItem(userId);
        model.addAttribute("items", recentGoods);
        return "/prod/recent";
    }

    // 구매한 제품 목록
    @GetMapping("/order")
    public String order(Model model){

        return "/prod/orderitem";
    }
}
