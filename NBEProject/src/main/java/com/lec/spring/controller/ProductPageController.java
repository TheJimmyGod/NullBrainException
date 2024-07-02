package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Cart;
import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.RecentItem;
import com.lec.spring.dto.OrderGoods;
import com.lec.spring.dto.OrderUser;
import com.lec.spring.repository.AddressRepo;
import com.lec.spring.service.CartService;
import com.lec.spring.service.GoodsService;
import com.lec.spring.service.RecentService;
import com.lec.spring.service.UserService;
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
    private final RecentService recentService;
    private final CartService cartService;

    private final UserService userService;



    @Autowired
    public ProductPageController(
        GoodsService goodsService,
        RecentService recentService,
        CartService cartService,
        UserService userService
    ){
        this.goodsService = goodsService;
        this.recentService = recentService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(){return "/cho/prod/main";}

    // 메인 페이지에서 카테고리 클릭시 제품 리스트 이동
    @GetMapping("/prodList")
    public String list(String category1, String category2, Integer page, Model model){
        goodsService.getProds(category1, category2, page, model);
        return "/cho/prod/list";
    }
    // 제품 상세 페이지
    @GetMapping("/detail/{good_no}")
    public String detail(@PathVariable String good_no,Integer page, Model model){
        goodsService.getReviews(good_no, page, model);
//        User user = U.getLoggedUser();
        String url = U.getRequest().getRequestURI();
        String currentUrl = "/nbe/detail/" + good_no;

        if(url.equals(currentUrl)){
            recentService.delete(1, good_no);
            recentService.addRecent(1, good_no);
        }
        return "/cho/prod/detail";
    }

// 상세페이지에서 구매혹은 장바구니로 이동
    @PostMapping("/prod/cart")
    public String addCart(String goodsNo, String option, Integer amount){
        User user = userService.findById(1);
        Goods goods = goodsService.getProd(goodsNo);
        Address addr = userService.getDefaultAddr(1);
        OrderUser orderUser = user.oderUser(addr.getStreet_addr(), addr.getDetail_addr());
        OrderGoods orderGoods = OrderGoods.builder()
                .goodsNo(goods.getGoods_no())
                .name(goods.getName())
                .price(goods.getPrice())
                .image(goods.getImage_1())
                .build();

        Cart item = Cart.builder()
                .opt(option)
                .amount(amount)
                .goods(orderGoods)
                .user(orderUser)
                .build();
        cartService.insert(item);
        return "redirect:/cart?userId=1";
    }


    // 제품 최근 목록
    @GetMapping("/recent")
    public String recent(Model model){
//        User user = U.getLoggedUser();
        List<Goods> recentGoods = new ArrayList<>();
        List<RecentItem> recentItems = recentService.getRecentItem(1);
        for(RecentItem r : recentItems){
            Goods goods = goodsService.getProd(r.getGoods_no());
            recentGoods.add(goods);
        }
        model.addAttribute("items", recentGoods);
        return "prod/recent";
    }


}
