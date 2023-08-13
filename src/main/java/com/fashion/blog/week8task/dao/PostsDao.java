package com.fashion.blog.week8task.dao;

import com.fashion.blog.week8task.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostsDao extends JpaRepository<Posts, Integer> {

    //TODO: WRITE CUSTOM QUERY FOR THIS
    @Query(value = "SELECT * FROM posts q ORDER BY q.id DESC",nativeQuery = true)
    List<Posts> findByKeyWord(String keyword);
    //List<Posts> findByCategory(String category);
   // @Query(value = "SELECT * FROM posts q ORDER BY q.id DESC",nativeQuery = true)

    //Posts findById(Integer id);
    Posts findByCategory(String category);

    Posts findByPostId(Integer id);
}




