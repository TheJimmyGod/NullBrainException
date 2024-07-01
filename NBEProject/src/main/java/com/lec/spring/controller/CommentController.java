package com.lec.spring.controller;

import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nbe/post/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/write/{post_id}")
    public QryResult write(
            @PathVariable("post_id") Integer post_id,
            @RequestParam("user_id") Integer userId,
            String content){
        return commentService.write(post_id, userId, content);
    }

    @PostMapping("/list/{post_id}")
    @ResponseBody
    public QryResult list(@PathVariable("post_id") Integer post_id){

        return commentService.list(post_id);
    }



    @ResponseBody
    @PostMapping("/delete/{post_id}")
    public QryResult delete(Integer id){return commentService.delete(id);}
}
