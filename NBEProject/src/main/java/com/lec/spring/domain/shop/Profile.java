package com.lec.spring.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    private Address[] addresses;
    private String nickName;
    private String phone;
    private MultipartFile profileImage;
}
