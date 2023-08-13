package com.fashion.blog.week8task.services;


import com.fashion.blog.week8task.dao.CommentsDao;
import com.fashion.blog.week8task.dao.PostsDao;
import com.fashion.blog.week8task.dto.CommentDto;
import com.fashion.blog.week8task.model.Comments;
import com.fashion.blog.week8task.model.Posts;
import com.fashion.blog.week8task.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServices {

    public final CommentsDao commentsDao;
    public final PostsDao  postsDao;
    @Autowired
    public CommentServices(CommentsDao commentsDao, PostsDao postsDao){
        this.commentsDao=commentsDao;
        this.postsDao=postsDao;
    }
    public ResponseEntity<String> save(CommentDto commentDto) {
        try{
            //GET ASSOCIATE POST NAME, EMAIL, CONTENT
            System.out.println(commentDto);
            Posts post = postsDao.findByPostId(commentDto.getPostId());
            Comments comments=new Comments();
            comments.setContent(commentDto.getComment());
            comments.setEmail(commentDto.getEmail());
            comments.setName(commentDto.getName());
            comments.setPostId(commentDto.getPostId());
            //comments.setPost(post);
            System.out.println("Before trying to save comment");
            System.out.println(comments);
            commentsDao.save(comments);
            return new ResponseEntity("Comment was successfully saved", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("Error saving comment",HttpStatus.NOT_IMPLEMENTED);
    }

//    public ResponseEntity<List<Comments>> findCommentByPostId(Integer postId) {
//        try{
//            return new ResponseEntity(commentsDao.findAllByPostId(postId),HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity(new ArrayList<Comments>(), HttpStatus.BAD_REQUEST);
//    }

    public ResponseEntity<Comments> findCommentById(Integer commentId) {
        try{
            return new ResponseEntity(commentsDao.findById(commentId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new Comments(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Comments>> findByKeyWords(String keyword) {
        try{
            return new ResponseEntity(commentsDao.findByKeywords(keyword),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<Comments>(),HttpStatus.BAD_REQUEST);
    }
}
