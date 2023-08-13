package com.fashion.blog.week8task.dao;

import com.fashion.blog.week8task.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao extends JpaRepository<Comments, Integer> {
    //List<Comments> findAllByPostPostId(Integer postId);
    @Query(value = "SELECT * FROM comments q ORDER BY q.comment_id DESC",nativeQuery = true)
    List<Comments> findByKeywords(String keyword);

    List<Comments> findAllByPostId(Integer id);

    //List<Comments> findAllByPostId(Integer postId);
}
