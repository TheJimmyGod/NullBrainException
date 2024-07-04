package com.lec.spring.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handException(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error"); // 에러 발생 시 보여줄 뷰 이름
        modelAndView.addObject("errorMessage", ex.getMessage()); // 에러 메시지 전달
        return modelAndView;
    }
}
