package com.lec.spring.config;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addUserToModel(Model model){
        User user = U.getLoggedUser();
        if(user != null){
            user = userService.findById(user.getId());
            model.addAttribute("userlog", user);
        } else{
            model.addAttribute("userlog", null);
        }
    }
}
