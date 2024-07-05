package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.ReviewGoods;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.dto.PayStatus;
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
    private PurchaseService purchaseService;

    @GetMapping("/request")
    public String request(Model model){
        User user = U.getLoggedUser();

        List<Purchase> PurchaseStatusList = purchaseService.getUserPayed(user.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("PurchaseStatusList", PurchaseStatusList);
        model.addAttribute("OK", PayStatus.OK);
        model.addAttribute("READY", PayStatus.READY);
        model.addAttribute("CANCEL", PayStatus.CANCEL);
        return "request";
    }






}
