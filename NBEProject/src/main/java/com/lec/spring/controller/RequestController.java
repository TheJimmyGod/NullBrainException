package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.ReviewGoods;

import com.lec.spring.service.PurchaseService;
import com.lec.spring.service.RequestService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class RequestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/request")
    public String request(Model model){
        User user = U.getLoggedUser();
        user = userService.findById(user.getId());
        List<ReviewGoods> PurchaseStatusList = requestService.listPurchaseStatus(user.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("PurchaseStatusList", PurchaseStatusList);

        return "request";
    }






}
