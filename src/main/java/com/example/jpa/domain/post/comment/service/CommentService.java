package com.example.jpa.domain.post.comment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.repository.CommentRepository;
import com.example.jpa.domain.post.post.entity.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment write(Post post, String body) {
        Comment comment = Comment.builder()
            .post(post)
            .body(body)
            .build();

        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public long count(){
        return commentRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    public Comment save(Comment c1) {
        return commentRepository.save(c1);
    }
}
