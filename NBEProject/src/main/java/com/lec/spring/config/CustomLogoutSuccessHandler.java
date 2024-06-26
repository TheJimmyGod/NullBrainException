package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(" 로그아웃 성공 ");

        LocalDateTime logoutTime = LocalDateTime.now();

        LocalDateTime loginTime = (LocalDateTime) request.getSession().getAttribute("loginTime");

        if(loginTime != null){
            long seconds = loginTime.until(logoutTime, ChronoUnit.SECONDS);

        }
        request.getSession().invalidate(); //로그아웃시 세션무효화

        String redirecUrl = "/user/login?logoutHandler";  //로그아웃후 리다이렉션

        if(request.getParameter("ret_url") != null){
            redirecUrl = request.getParameter("ret_url");
        }

        response.sendRedirect(redirecUrl);



    }
}
