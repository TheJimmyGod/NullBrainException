package com.lec.spring.controller;

import com.lec.spring.domain.PostValidator;
import com.lec.spring.domain.shop.Post;
import com.lec.spring.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    public PostController() {
        System.out.println("PostController() 생성");
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Post post,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            )
    {
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", post.getUser());
            redirectAttributes.addFlashAttribute("content", post.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_"+err.getField(), err.getCode());
            }
            return "redirect:/post/write";
        }

        model.addAttribute("result", postService.write(post, files));
        return "post/writeOk";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("post", postService.selectById(id));
        return "post/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Post post,
            BindingResult result,
            Integer[] delfile,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("content", post.getContent());
            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_" + err.getField(), err.getCode());
            }
            return "redirect:/post/update/" + post.getId();
        }

        model.addAttribute("result", postService.update(post, files, delfile));
        return "post/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Integer id, Model model){
        model.addAttribute("result", postService.deleteById(id));
        return "post/deleteOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("PostController.initBinder() 호출");
        binder.setValidator(new PostValidator());
    }

//    @PostMapping("/pageRows")
//    public String pageRows(Integer page, Integer pageRows){
//        // U.getSession().setAttribute("pageRows",pageRows);
//        return "redirect:/post/list?page=" + page;
//    }
}
