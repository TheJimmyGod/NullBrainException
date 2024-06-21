package com.lec.spring.controller;

import com.lec.spring.dto.UserDto;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public void login(){}

    @GetMapping("/register")
    public void register(){


    }

    @PostMapping("/register")  //아직 검증로직 x 두번째 레지스터부터는 오류 존재함
    public String registerOk(@Valid @ModelAttribute UserDto userDto, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("username", userDto.getUsername());
            redirectAttributes.addFlashAttribute("name", userDto.getName());
            redirectAttributes.addFlashAttribute("phone", userDto.getPhone());
            redirectAttributes.addFlashAttribute("birth", userDto.getBirth());
            redirectAttributes.addFlashAttribute("email", userDto.getEmail());
            redirectAttributes.addFlashAttribute("gender", userDto.getGender());
            redirectAttributes.addFlashAttribute("street_addr", userDto.getStreet_addr());
            redirectAttributes.addFlashAttribute("detail_addr", userDto.getDetail_addr());
            redirectAttributes.addFlashAttribute("addressname", userDto.getAddressName());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                redirectAttributes.addFlashAttribute("error", err.getCode());
                break;
            }

            return "redirect:/user/register";

        }
        int cnt = userService.register(userDto);

        String page = "/user/registerOk";

        model.addAttribute("result", cnt);
        return page;
    }

}
