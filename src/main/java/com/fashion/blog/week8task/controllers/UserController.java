package com.fashion.blog.week8task.controllers;

import com.fashion.blog.week8task.dto.LoginDto;
import com.fashion.blog.week8task.dto.ResponseDto;
import com.fashion.blog.week8task.model.Users;
import com.fashion.blog.week8task.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fashion.blog.week8task.model.Posts;

@RestController
@RequestMapping("user")
public class UserController {
    private  UserServices userServices;
    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveUser(@RequestBody Users user){
        return userServices.saveUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto, HttpSession session){
        return userServices.auth(loginDto,session);
    }

    @PostMapping("logout")
    public ResponseEntity<String>  logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity("Logout successful", HttpStatus.OK);
    }

}