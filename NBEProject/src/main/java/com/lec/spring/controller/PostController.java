package com.lec.spring.controller;

import com.lec.spring.domain.ImageFileValidator;
import com.lec.spring.domain.shop.Post;
import com.lec.spring.domain.PostValidator;
import com.lec.spring.service.LikeService;
import com.lec.spring.service.PostService;
import com.lec.spring.util.U;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @Autowired
    private LikeService likeService;

    @Autowired
    private ImageFileValidator imageFileValidator;

    @Autowired
    private PostValidator postValidator;

    public PostController() {
        System.out.println("PostController() 생성");
    }

    @GetMapping("/write")
    public void write(Model model){
        model.addAttribute("username", "Tommy");
    }

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Post post,
            BindingResult result,
            BindingResult filesResult,
            Model model,
            RedirectAttributes redirectAttributes
            )
    {
        postValidator.validate(post, result);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", post.getUser());
            redirectAttributes.addFlashAttribute("content", post.getContent());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_"+err.getField(), err.getCode());
            }
            return "redirect:/post/write";
        }

        imageFileValidator.validate(files, filesResult);

        if(filesResult.hasErrors()){
            redirectAttributes.addFlashAttribute("imageList", files);

            List<FieldError> errList = filesResult.getFieldErrors();

            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_"+err.getField(), err.getCode());
            }
            return "redirect:/post/write";
        }

        model.addAttribute("result", postService.write(post, files));
        return "post/writeOk";
    }
    @GetMapping("/list")
    public void list(Integer page, Model model){
        postService.list(page, model);
        //model.addAttribute("list", postService.list());
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("post", postService.selectById(id));
        return "post/update";
    }

    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files,
            Post post,
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

    @PostMapping("/list/{post_id}")
    @ResponseBody
    public String likePost(@PathVariable Integer post_id, @RequestParam Integer user_id) {
        likeService.likePost(post_id, user_id);
        return "redirect:/post/list";
    }
    @GetMapping("/comment/{post_id}")
    public String commentDetail(@PathVariable Integer post_id, Model model){
        Post post = postService.detail(post_id);
        model.addAttribute("post", post);
        return "post/comment";
    }

    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows",pageRows);
        return "redirect:/post/list?page=" + page;
    }
}
