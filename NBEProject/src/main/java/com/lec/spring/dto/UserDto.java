package com.lec.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //User 부분

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8,max = 16, message = "비밀번호는 8~16자로 구성되어야합니다.")
    @ToString.Exclude
    @JsonIgnore
    private String password;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "전화번호를 입력해주세요")
    private String phone;
    @NotBlank(message = "출생연월을 입력해주세요")
    private String birth;
    @Email(message = "유효한 이메일이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;
    @NotNull(message = "성별은 필수 사항입니다.")
    private Integer gender;


    //Address 부분

    private Integer id;
    final private Boolean is_default = true;
    @NotBlank(message = "주소를 입력해주세요")
    private String street_addr;
    @NotBlank(message = "상세 주소를 입력해주세요")
    private String detail_addr;
    @NotBlank(message = "주소지명을 입력해주세요")
    private String addressName;
}
