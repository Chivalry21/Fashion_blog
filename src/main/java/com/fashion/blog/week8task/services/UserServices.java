package com.fashion.blog.week8task.services;

import com.fashion.blog.week8task.dao.UsersDao;
import com.fashion.blog.week8task.dto.LoginDto;
import com.fashion.blog.week8task.dto.ResponseDto;
import com.fashion.blog.week8task.dto.UserDto;
import com.fashion.blog.week8task.model.Users;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Data
@Slf4j
@Service
public class UserServices {
    private UsersDao usersDao;

    @Autowired
    public UserServices(UsersDao usersDao){
        this.usersDao=usersDao;
    }
    public ResponseEntity<String> saveUser(Users users){
        try{
            String password = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
            users.setPassword(password);
            usersDao.save(users);
            return new ResponseEntity("User account has been created", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("Error saving user account", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseDto> auth(LoginDto loginDto, HttpSession session) {
        log.info(String.valueOf(loginDto));
        try{
            Users user = usersDao.findByEmail(loginDto.getEmail());
            if(user==null){
                return new ResponseEntity(new ResponseDto(401,"Failed","Invalid login credentials"),HttpStatus.NOT_FOUND);
            }
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                UserDto userDto;
                userDto=new UserDto();
                userDto.setEmail(user.getEmail());
                userDto.setFullName(user.getFullName());
                userDto.setUserId(user.getId());
                session.setAttribute("userDTO",userDto);
                session.setAttribute("loggedInUser",userDto.getFullName());
                session.setAttribute("loggedInId",userDto.getUserId());
                return new ResponseEntity(new ResponseDto(200,"Success","User has been authenticated"),HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(new ResponseDto(401,"Failed","Invalid login credentials"),HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new ResponseDto(418,"I'm a teapot","The server refuses the attempt to brew coffee with a teapot."),HttpStatus.BAD_REQUEST);
    }
}
