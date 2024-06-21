package com.lec.spring.controller;
import com.lec.spring.domain.shop.Goods;
import com.lec.spring.domain.shop.Opt;
import com.lec.spring.repository.ItemRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    ItemRepo goodsRepo;
    @Autowired
    public HomeController(SqlSession sqlSession){
        goodsRepo = sqlSession.getMapper(ItemRepo.class);
    }
    RestTemplate rt = new RestTemplate();

    @GetMapping("/product")
    @ResponseBody
    public String getInfo(){
        List<String> man = new ArrayList<>();
        List<String> woman = new ArrayList<>();
        man.add("570501010");
        man.add("570501020");
        man.add("570501030");
        man.add("570501040");
        man.add("570501050");
        man.add("570502010");
        man.add("570502020");
        man.add("570502030");
        man.add("570502040");
        man.add("570502050");
        man.add("570502060");
        man.add("570502070");
        man.add("570502080");
        man.add("570503010");
        man.add("570503020");
        man.add("570504010");
        man.add("570504020");
        man.add("570504030");
        man.add("570504040");
        man.add("570504050");
        man.add("570505010");
        man.add("570505020");
        man.add("570505030");
        man.add("570505040");
        man.add("570505050");
        man.add("570505060");
        man.add("570505070");
        man.add("570505080");
        man.add("570506010");
        man.add("570506030");
        man.add("570506040");
        man.add("570506050");
        man.add("570506060");
        man.add("570507010");
        man.add("570507020");
        man.add("570507030");
        man.add("570507040");

        woman.add("543501010");
        woman.add("543501020");
        woman.add("543501030");
        woman.add("543501040");
        woman.add("543501050");
        woman.add("543502010");
        woman.add("543502020");
        woman.add("543502030");
        woman.add("543502040");
        woman.add("543502050");
        woman.add("543503010");
        woman.add("543503020");
        woman.add("543503030");
        woman.add("543503040");
        woman.add("543503050");
        woman.add("543503060");
        woman.add("543503070");
        woman.add("543503080");
        woman.add("543503090");
        woman.add("543503100");
        woman.add("543503110");
        woman.add("543503120");
        woman.add("543504010");
        woman.add("543504020");
        woman.add("543505010");
        woman.add("543505020");
        woman.add("543505030");
        woman.add("543505040");
        woman.add("543505050");
        woman.add("543505060");
        woman.add("543506010");
        woman.add("543506020");
        woman.add("543506030");
        woman.add("543506040");
        woman.add("543506050");
        woman.add("543506060");
        woman.add("543506070");
        woman.add("543506080");
        woman.add("543506090");
        woman.add("543506100");
        woman.add("543506110");
        woman.add("543506120");
        woman.add("543507010");
        woman.add("543507020");
        woman.add("543507030");
        woman.add("543507040");
        woman.add("543507050");
        woman.add("543507060");
        woman.add("543507070");
        woman.add("543507080");
        woman.add("543507090");
        woman.add("543507100");
        woman.add("543508010");
        woman.add("543508020");
        woman.add("543508030");
        woman.add("543508040");
        woman.add("543508050");
        woman.add("543508060");
        woman.add("543508070");
        woman.add("543508080");
        woman.add("543508090");
        woman.add("543509010");
        woman.add("543509020");
        woman.add("543509030");
        woman.add("543509040");
        woman.add("543509050");
        woman.add("543509060");
        woman.add("543509070");
        woman.add("543509080");
        woman.add("543509090");
        woman.add("543510010");
        woman.add("543510020");
        woman.add("543510030");
        woman.add("543510040");
        woman.add("543510050");
        woman.add("543510060");
        woman.add("543510070");
        woman.add("543510080");
        woman.add("543510090");
        woman.add("543511010");
        woman.add("543511020");
        woman.add("543512010");
        woman.add("543512020");
        woman.add("543512030");
        woman.add("543512040");
        woman.add("543512050");
        woman.add("543512060");
        woman.add("543512070");
        woman.add("543512080");
        woman.add("543513010");
        woman.add("543513020");
        woman.add("543513030");
        woman.add("543513040");
        woman.add("543513050");
        woman.add("543513060");
        woman.add("543513070");
        woman.add("543513080");
        woman.add("543513090");
        woman.add("543513100");
        woman.add("543513110");
        woman.add("543514010");
        woman.add("543514020");
        woman.add("543514030");
        woman.add("543514040");
        woman.add("543514050");
        woman.add("543514060");
        woman.add("543514070");
        woman.add("543514080");
        woman.add("543514090");
        woman.add("543514100");
        woman.add("543515010");
        woman.add("543515020");
        woman.add("543515030");
        woman.add("543515040");
        woman.add("543515050");
        woman.add("543515060");
        woman.add("543516010");
        woman.add("543516020");
        woman.add("543516030");
        woman.add("543516040");
        woman.add("543516050");
        woman.add("543517010");
        woman.add("543517020");
        woman.add("543517030");
        woman.add("543518010");
        woman.add("543518020");
        woman.add("543518030");
        woman.add("543519010");
        woman.add("543519020");
        woman.add("543519030");
        woman.add("543519040");
        woman.add("543519050");
        woman.add("543519060");
        woman.add("543519070");
        woman.add("543519080");
        woman.add("543520010");
        woman.add("543520020");
        woman.add("543520030");
        woman.add("543521010");
        woman.add("543521020");
        woman.add("543521030");
        woman.add("543521040");
        woman.add("543521050");
        woman.add("543521060");
        woman.add("543521070");
        woman.add("543521080");
        woman.add("543521090");
        woman.add("543521100");

        rt.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("d8aa29ebc8d72cb49aa27db99f02bee0f8124cc56ccde5d43f6a098ba649f272");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        for(String id : man){

            URI url = UriComponentsBuilder.fromUriString("https://specialoffer.kr/api/goods")
                    .queryParam("category_code", id)
                    .build()
                    .encode()
                    .toUri();

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Goods> response = rt.exchange(url.toString(), HttpMethod.GET, entity, Goods.class);
            Goods responseBody = response.getBody();
            responseBody.getData().forEach(e -> {
                goodsRepo.insert(e);
//                List<Map<String, String>> opt = new ArrayList<>();
//                List<String> titles = e.getOption_titles();
//                List<Opt> options = e.getOption_values();

            });
        }

        for(String id : woman){

            URI url = UriComponentsBuilder.fromUriString("https://specialoffer.kr/api/goods")
                    .queryParam("category_code", id)
                    .build()
                    .encode()
                    .toUri();

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Goods> response = rt.exchange(url.toString(), HttpMethod.GET, entity, Goods.class);
            Goods responseBody = response.getBody();
            responseBody.getData().forEach(e -> {
                goodsRepo.insert(e);
//                List<Map<String, String>> opt = new ArrayList<>();
//                List<String> titles = e.getOption_titles();
//                List<Opt> options = e.getOption_values();

            });
        }
        return "success";
    }


}