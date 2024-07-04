package com.lec.spring.domain.shop;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Profile profile = (Profile) target;
        var phoneRegex = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
        var regex = "^[가-힣a-zA-Z]+$";

        if(Objects.equals(profile.getNickName(), ""))
        {
            errors.rejectValue("name", "닉네임이 비어있습니다.");
        }

        if(profile.getNickName() != null && !Pattern.matches(regex, profile.getNickName())){
            errors.rejectValue("name", "올바르지 않은 형식의 닉네임입니다.");
        }

        if(Objects.equals(profile.getPhone(), ""))
        {
            errors.rejectValue("phone", "전화번호가 비어있습니다.");
        }

        if(profile.getPhone() != null && !Pattern.matches(phoneRegex, profile.getPhone())){
            errors.rejectValue("phone", "올바르지 않은 형식의 전화번호입니다.");
        }

        var it = profile.getProfileImage().getOriginalFilename();

        if(it != null)
        {
            File checkfile = new File(Objects.requireNonNull(it));
            String type = null;
            try {
                type = Files.probeContentType(checkfile.toPath());
                System.out.println("This is " + type);
                if(type == null)
                {
                    errors.rejectValue("file", "비어있는 파일입니다.");
                }
                if(type != null && !type.contains("image"))
                {
                    errors.rejectValue("file", "반드시 이미지 파일이여야만 합니다.");
                }

            } catch (IOException e) {
                errors.rejectValue("file", "비어있는 파일입니다.");
            } catch (NullPointerException e)
            {
                errors.rejectValue("file", "비어있는 파일입니다.");
            }

        }

        System.out.println("Profile Validator Complete");

    }
}
