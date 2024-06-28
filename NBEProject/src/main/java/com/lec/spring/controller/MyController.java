package com.lec.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Item;
import com.lec.spring.domain.shop.Profile;
import com.lec.spring.domain.shop.ProfileValidator;
import com.lec.spring.service.MyService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/mypage")
public class MyController {
    @Autowired
    private ProfileValidator profileValidator;
    @Autowired
    private MyService myService;

    @Autowired
    private UserService userService;

    public MyController() {
        System.out.println("MyController() 생성");
    }

    @GetMapping("/detail")
    public void detail(Model model)
    {
        myService.showMyPage(model);
    }

    @GetMapping("/update")
    public void update(Model model){
        myService.updateMyPage(model, 2);
    }

    @ResponseBody
    @PostMapping("/update")
    public String updateOk(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("nickname") String nickname,
            @RequestParam("phone") String phone,
            @RequestParam("addresses") String addressesJson,
            Model model
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        Address[] addresses;
        try {
            addresses = objectMapper.readValue(addressesJson, Address[].class); // JSON 문자열을 Address 배열로 변환
        } catch (JsonProcessingException e) {
            return "redirect:/mypage/update";
        }
        Profile profile = new Profile(addresses,nickname, phone, file);
        model.addAttribute("result", myService.updateProfile(2, profile));
        return "mypage/update";
    }

    @GetMapping("/createAddress")
    public void createAddress(Model model){
    }

    @GetMapping("/updateAddress")
    public void updateAddress(Model model){
    }

    @GetMapping("/createReview")
    public void createReview(Model model){
        Item testItem = new Item();
        testItem.setName("옷");

        model.addAttribute("Goods", testItem);
        model.addAttribute("GoodsCount", 1);
        model.addAttribute("GoodsSize", "XL");
        model.addAttribute("GoodsColor", "Red");
    }

}
