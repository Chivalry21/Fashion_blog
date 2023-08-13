package com.fashion.blog.week8task.model;

import com.fashion.blog.week8task.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


    public Users(UserDto userDto){
        id=userDto.getUserId();
        fullName=userDto.getFullName();
        email=userDto.getEmail();
    }

}
