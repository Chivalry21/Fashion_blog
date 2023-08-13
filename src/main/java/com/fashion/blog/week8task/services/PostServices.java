package com.fashion.blog.week8task.services;


import com.fashion.blog.week8task.dao.CommentsDao;
import com.fashion.blog.week8task.dao.PostsDao;
import com.fashion.blog.week8task.dto.PostDto;
import com.fashion.blog.week8task.dto.UserDto;
import com.fashion.blog.week8task.model.Comments;
import com.fashion.blog.week8task.model.Posts;
import com.fashion.blog.week8task.model.Users;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServices {
    private  PostsDao postsDao;
    private CommentsDao commentsDao;

    @Autowired
    public PostServices(PostsDao postsDao, CommentsDao commentsDao){
        this.postsDao=postsDao;
        this.commentsDao=commentsDao;
    }
    public ResponseEntity<String> save(Posts posts, HttpSession session) {
        try{
            //CHECK IS USER IS AUTHENTICATED
            UserDto userDto=(UserDto)session.getAttribute("userDTO");
            if(userDto==null){
                return new ResponseEntity("You must login in to Save Post", HttpStatus.UNAUTHORIZED);
            }
            log.info(String.valueOf(userDto));
            posts.setUser(new Users(userDto));
            postsDao.save(posts);
            return new ResponseEntity("New Post Successfully saved", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("Error saving Post", HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Posts>> fetchAll() {
        try{
            return new ResponseEntity(postsDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<Posts>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<PostDto> findByPostId(Integer id){
        try{
            Posts posts= postsDao.findByPostId(id);
            if(posts==null){
                return  new ResponseEntity(new PostDto(),HttpStatus.OK);
            }
            List<Comments> comments=commentsDao.findAllByPostId(id);
            PostDto postDto=new PostDto(comments,posts);
            log.info(String.valueOf(posts));
            return new ResponseEntity(postDto,HttpStatus.OK);
            //Users user = usersDao.findByEmail(loginDto.getEmail());

            //System.out.println("Retrieved comments");
            //System.out.println(comments);

            //return new ResponseEntity(postDto,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
       return new ResponseEntity(new Posts(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Posts>> findPostsByKeyWords(String keyWords) {
        try{
            new ResponseEntity(postsDao.findByKeyWord(keyWords),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity(new ArrayList<Posts>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Posts>> findByCategory(String category) {
        try{
            return new ResponseEntity(postsDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<Posts>(),HttpStatus.BAD_REQUEST);
    }
}
