package com.lec.spring.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Post.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Post post = (Post)target;
        System.out.println("Post-Validator-validate() 호출: " + post);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image", "이미지는 필수입니다.");
    }
}
