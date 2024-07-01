package com.lec.spring.controller;

import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/request")
    public String request(@RequestParam("userId") Integer userId, Model model){
        List<Purchase> PurchaseStatusList = requestService.listPurchaseStatus(userId);

        model.addAttribute("PurchaseStatusList", PurchaseStatusList);

        return "request";
    }



}
