package com.fashion.blog.week8task;

import com.fashion.blog.week8task.controllers.PostController;
import com.fashion.blog.week8task.controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class Week8TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week8TaskApplication.class, args);
    }

}
