package com.lec.spring.config;

import com.lec.spring.domain.User;
import com.lec.spring.repository.CartRepo;
import com.lec.spring.service.CartService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username); //id로 User리턴

        if(user != null){
            int cartCnt = cartService.listUserItems(user.getId()).size();
            HttpSession session = U.getSession();
            session.setAttribute("cartCnt", cartCnt);
            PrincipalDetails userDetails = new PrincipalDetails(user);
            userDetails.setUserService(userService);
            return userDetails;
        }

        throw new UsernameNotFoundException(username);
    }
}
