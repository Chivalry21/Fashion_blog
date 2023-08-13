package com.fashion.blog.week8task.dto;

import com.fashion.blog.week8task.model.Comments;
import com.fashion.blog.week8task.model.Posts;
import com.fashion.blog.week8task.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String category;
    private Integer likes;
    private String createdAt;
    private String updatedAt;
    private List<Comments> comments;

    public PostDto(List<Comments> comments, Posts posts){
        this.id=posts.getPostId();
        title=posts.getTitle();
        content=posts.getContent();
        category=posts.getCategory();
        likes=posts.getLikes();
        createdAt= String.valueOf(posts.getCreatedAt());
        updatedAt= String.valueOf(posts.getUpdatedAt());
        this.comments=comments;
    }
}
