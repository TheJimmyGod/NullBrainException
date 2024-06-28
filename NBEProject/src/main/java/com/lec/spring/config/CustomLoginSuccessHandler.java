package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public CustomLoginSuccessHandler(String defaultTargetUrl){
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        System.out.println( "로그인 성공 ");

        PrincipalDetails userDetails = (PrincipalDetails)authentication.getPrincipal();

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });
        System.out.println("권한: " + roleNames);

        LocalDateTime loginTime = LocalDateTime.now();
        request.getSession().setAttribute("loginTime", loginTime);

        if(roleNames.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin/main");
            return;
        }


        super.onAuthenticationSuccess(request, response, authentication);
        
    }
}
