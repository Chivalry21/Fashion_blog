package com.fashion.blog.week8task.dao;

import com.fashion.blog.week8task.dto.LoginDto;
import com.fashion.blog.week8task.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDao extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);
}
