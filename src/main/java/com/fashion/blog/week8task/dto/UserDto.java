package com.fashion.blog.week8task.dto;

import com.fashion.blog.week8task.model.Users;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer userId;
    private String fullName;
    private String email;
    public UserDto(Users users){
        userId=users.getId();
        fullName=users.getFullName();
        email=getEmail();
    }
}
