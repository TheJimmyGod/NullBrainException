package com.lec.spring.controller;

import com.lec.spring.config.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/check")
public class AuthController {
    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth() {return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/userDetails")
    @ResponseBody
    public PrincipalDetails userDetails(Authentication authentication){
        return (PrincipalDetails) authentication.getPrincipal();
    }



}
