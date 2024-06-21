package com.lec.spring.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Component
public class ImageFileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Map<String, MultipartFile> files = (Map<String, MultipartFile>)target;
        StringBuffer str = new StringBuffer();
        if(files.isEmpty())
        {
            errors.rejectValue("imageList", "이미지는 필수입니다.");
        }
        int i = 1;
        for(var item : files.entrySet())
        {

            if(item.getValue() == null)
            {
                str.append((i) +  "번의 이미지가 존재하지 않습니다.");
                errors.rejectValue("imageList", str.toString());
            }
            else
            {
                File checkfile = new File(Objects.requireNonNull(item.getValue().getOriginalFilename()));
                String type = null;
                try {
                    type = Files.probeContentType(checkfile.toPath());
                    System.out.println("This is " + type);
                    if(type == null)
                    {
                        errors.rejectValue("imageList", "비어있는 파일입니다.");
                    }
                    if(type != null && !type.contains("image"))
                    {
                        errors.rejectValue("imageList", "반드시 이미지 파일이여야만 합니다.");
                    }

                } catch (IOException e) {
                    errors.rejectValue("imageList", "비어있는 파일입니다.");
                } catch (NullPointerException e)
                {
                    errors.rejectValue("imageList", "비어있는 파일입니다.");
                }
            }
            i++;
        }
        System.out.println("File Validator Complete");
    }
}
