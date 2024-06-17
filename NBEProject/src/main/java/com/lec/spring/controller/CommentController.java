package com.lec.spring.controller;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/list/{postId}")
    public QryCommentList list(@PathVariable Integer postId) {return commentService.list(postId);}

    @PostMapping("/write")
    public QryResult write(
            @RequestParam("post_id") Integer postId,
            @RequestParam("user_id") Integer userId,
            String content){
        return commentService.write(postId, userId, content);
    }

    @PostMapping("/delete")
    public QryResult delete(Integer id){return commentService.delete(id);}
}
