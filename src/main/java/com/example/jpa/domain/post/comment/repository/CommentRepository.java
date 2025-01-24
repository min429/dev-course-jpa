package com.example.jpa.domain.post.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.domain.post.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
