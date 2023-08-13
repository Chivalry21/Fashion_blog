package com.fashion.blog.week8task.controllers;

import com.fashion.blog.week8task.dto.CommentDto;
import com.fashion.blog.week8task.model.Comments;
import com.fashion.blog.week8task.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentServices commentServices;

    @Autowired
    public  CommentController(CommentServices commentServices){
        this.commentServices=commentServices;
    }

    @PostMapping (value = "save", consumes = {"application/json"})
    public ResponseEntity<String> save(@RequestBody CommentDto commentDto){
        System.out.println("before receipt");
        System.out.println(commentDto);
        System.out.println("after receipt");
        return commentServices.save(commentDto);
    }

    @GetMapping("/find/{commentId}")
    public ResponseEntity<Comments> findCommentById(@PathVariable(value = "commentId") Integer commentId){
        return commentServices.findCommentById(commentId);
    }

    @GetMapping("/find/keywords/{keyword}")
    public ResponseEntity<List<Comments>> findByKeyWords(@PathVariable(value = "keyWord") String keyword){
        return commentServices.findByKeyWords(keyword);
    }
}
