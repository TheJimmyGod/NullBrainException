package com.lec.spring.controller;
import com.lec.spring.domain.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
 * query   String   Y  검색어.UTF-8 로 인코딩되어야됨
 * display Integer  N  한번에 표시할 검색결과 개수(기본값: 10, 최댓값: 100)
 * start   Integer  N  검색 시작위치 (기본값: 1, 최댓값: 100)
 * sort    String   N  검색 결과 정렬방법
 *                     -sim  : 정확도순으로 내림차순 정렬(기본값)
 *                     -date : 날짜순으로 내림차순 정렬
 *                     -asc  : 가격순으로 오름차순 정렬
 *                     -dsc  : 가격순으로 내림차순 정렬
 * filter  String  N  검색 결과에 포함할 상품 유형 - 설정 안함: 모든상품(기본값)  - naverpay: 네이버페이 연동 상품
 * exclude String  N  검색결과에서 제외할 상품유형. exclude={option}:{option}:{option}
 *                    형태로 설정(예: exclude=used:cbshop).
 *                    -used  : 중고
 *                    -rental: 렌탈
 *                    -cbshop: 해외직구, 구매대행
 * */
@RequestMapping("/shop")
@Controller
public class HomeController {
    String requestUrl = "https://openapi.naver.com/v1/search/shop.json";

    RestTemplate rt = new RestTemplate();

    @GetMapping("/hello")
    public String hello(){return "hello";}
    @GetMapping("/product")
    public String getInfo(Model model){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Naver-Client-Id","u8KkPZWhFC1zC65uYoqr");
        headers.add("X-Naver-Client-Secret","Ls8LfoYruJ");

        // HttpEntity 생성
        HttpEntity<MultiValueMap<String, String>> entity =
                new HttpEntity<>(headers);
        // 요청
        ResponseEntity<?> response = rt.exchange(requestUrl +"?query={query}",
                HttpMethod.GET,
                entity,
                Product.class,
                "셔츠");

        Product p = (Product) response.getBody();
        List<String> images = new ArrayList<>();
        p.getItems().stream().forEach(e -> images.add(e.getImage()));
        model.addAttribute("images", images);

        return "/product";
    }


}