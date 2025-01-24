package com.example.jpa.domain.post.post.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.domain.post.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);
    List<Post> findByTitleAndBody(String title, String body);
    List<Post> findByTitleLike(String title);
    List<Post> findByOrderByIdDesc();
    List<Post> findTop1ByTitleOrderByIdDesc(String title);
    Page<Post> findByTitleLike(String title, Pageable pageable);
    List<Post> findByAuthorUsername(String username);
}
