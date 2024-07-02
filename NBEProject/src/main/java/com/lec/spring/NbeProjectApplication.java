package com.lec.spring;

import com.lec.spring.dto.PayStatus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@MapperScan("com.lec.spring.repository")
public class NbeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbeProjectApplication.class, args);
    }

}

