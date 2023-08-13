package com.fashion.blog.week8task.controllers;

import com.fashion.blog.week8task.dto.PostDto;
import com.fashion.blog.week8task.model.Posts;
import com.fashion.blog.week8task.services.PostServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostServices postServices;


    @Autowired
    public PostController(PostServices postServices){
        this.postServices=postServices;
    }

    @PostMapping(value = "add", consumes = {"application/json"})
    public ResponseEntity<String> save(@RequestBody Posts posts, HttpSession session) {
        return postServices.save(posts, session);
    }


    @RequestMapping("all")
    public ResponseEntity<List<Posts>> allPost(){
        return postServices.fetchAll();
    }

    @GetMapping("post/{postId}")
    public  ResponseEntity<PostDto> findPostById(@PathVariable(value = "postId") Integer postId){
        return postServices.findByPostId(postId);
    }

    @GetMapping("findContains/{keywords}")
    public ResponseEntity<List<Posts>> findPostsByKeyWord(@PathVariable(value = "keywords") String keywords){
        return postServices.findPostsByKeyWords(keywords);
    }
    @GetMapping("search/{category}")
    public ResponseEntity<List<Posts>> findByCategory(@PathVariable(value = "category") String category ){
        return postServices.findByCategory(category);
    }

}

