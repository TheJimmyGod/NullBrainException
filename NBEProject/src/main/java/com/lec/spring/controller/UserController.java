package com.lec.spring.controller;

import com.lec.spring.dto.UserDto;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public void login(){}

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")  //아직 검증로직 x 두번째 레지스터부터는 오류 존재함
    public String registerOk(@Valid @ModelAttribute UserDto userDto, Model model){
        int cnt = userService.register(userDto);

        String page = "/user/registerOk";

        model.addAttribute("result", cnt);
        return page;
    }

}
