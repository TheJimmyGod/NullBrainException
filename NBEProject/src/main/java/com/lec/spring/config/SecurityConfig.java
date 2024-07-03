package com.lec.spring.config;

import com.lec.spring.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring().anyRequest();
//    }

    // oAuth2 Client
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/post/**").authenticated()
//                        .requestMatchers("/post/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole( "ADMIN")
//                        .requestMatchers("/**").hasAnyRole("!USER_BAN")
                        .anyRequest().permitAll()
                ) //end auth -> auth.~
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")  //loadUserBy.. 필요
                        //.defaultSuccessUrl("/user/test")
                        .successHandler(new CustomLoginSuccessHandler("/nbe/home"))
                        .failureHandler(new CustomLoginFailureHandler())
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(false)
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .accessDeniedHandler(new CustomAccessDeniedHandler()))

                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
                        .loginPage("/user/login") //로그인 페이지 url

                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(principalOauth2UserService) // userService(OAuth2UserService)
                        )
                        .successHandler(new CustomOAuth2LoginSuccessHandler("/user/test"))
                        .failureHandler((request, response, exception) -> {
                            exception.printStackTrace();
                        })


                )

                .build();
    }//end filterChain()

    //oAuth2 로그인
    //AuthenticationManager Bean 생성
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }


}
