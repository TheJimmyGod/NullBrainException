package com.lec.spring.domain;

import com.lec.spring.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class RegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        String regex = "^[가-힣a-zA-Z0-9]+$";
        String nameRegex = "^[가-힣a-zA-Z0-9\s]+$";
        String birthRegex = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
        String phoneRegex = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if(userDto.getUsername()==null || userDto.getUsername().isEmpty())
        {
            errors.rejectValue("username", "아이디 입력은 필수입니다.");
        }
        else
        {
            if(!Pattern.matches(regex, userDto.getUsername()))
            {
                errors.rejectValue("username", "입력된 아이디가 양식에 맞지 않습니다.");
            }
        }

        if(userDto.getName()==null || userDto.getName().isEmpty())
        {
            errors.rejectValue("name", "이름 입력은 필수입니다.");
        }
        else
        {
            if(!Pattern.matches(nameRegex, userDto.getName()))
            {
                errors.rejectValue("name", "입력된 이름이 양식에 맞지 않습니다.");
            }
        }

        if(userDto.getPhone()==null || userDto.getPhone().isEmpty())
        {
            errors.rejectValue("phone", "휴대폰 번호 입력은 필수입니다.");
        }
        else
        {
            if(!Pattern.matches(phoneRegex, userDto.getPhone()))
            {
                errors.rejectValue("phone", "입력된 휴대폰 번호이 양식에 맞지 않습니다. (예: 000-0000-0000)");
            }
        }
        if(userDto.getEmail()==null || userDto.getEmail().isEmpty())
        {
            errors.rejectValue("phone", "휴대폰 번호 입력은 필수입니다.");
        }
        else
        {
            if(!Pattern.matches(emailRegex, userDto.getEmail()))
            {
                errors.rejectValue("email", "입력된 이메일이 양식에 맞지 않습니다. (예: xxxxx@naver.com)");
            }
        }

        if(userDto.getBirth()==null || userDto.getBirth().isEmpty())
        {
            errors.rejectValue("birth", "생일 입력은 필수입니다.");
        }
        else
        {
            if(!Pattern.matches(birthRegex, userDto.getBirth()))
            {
                errors.rejectValue("birth", "입력된 생년월일이 양식에 맞지 않습니다.(예: 19921120)");
            }
        }

        if(userDto.getStreet_addr() == null || userDto.getStreet_addr().isEmpty())
        {
            errors.rejectValue("street_addr", "도로명주소 입력은 필수입니다.");
        }
        if(userDto.getDetail_addr() == null || userDto.getDetail_addr().isEmpty())
        {
            errors.rejectValue("detail_addr", "상세주소 입력은 필수입니다.");
        }
        System.out.println("Register Validator Complete");
    }
}
