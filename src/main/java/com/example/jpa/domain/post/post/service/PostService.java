package com.example.jpa.domain.post.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post write(Member author, String title, String body) {

        // 1. Post 조립
        Post post = Post.builder()
            .title(title)
            .body(body)
            .author(author)
            .build();

        return postRepository.save(post);
    }

    public Post modify(Post post, String title, String body){
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    public void modify2(long id, String title, String body){
        Post post = postRepository.findById(id).get(); // DB반영이 끝나도 트랜잭션이 끝나지 않음

        post.setTitle(title);
        post.setBody(body);
    }

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
    
    public List<Post> findByTitleAndBody(String title, String body) {
        return postRepository.findByTitleAndBody(title, body);
    }

    public List<Post> findByTitleLike(String title) {
        return postRepository.findByTitleLike("%" + title + "%");
    }

    public Page<Post> findByTitleLike(String title, Pageable pageable) {
        return postRepository.findByTitleLike("%" + title + "%", pageable);
    }

    public List<Post> findByOrderByIdDesc() {
        return postRepository.findByOrderByIdDesc();
    }
    
    public List<Post> findTop1ByTitleOrderByIdDesc(String title) {
        return postRepository.findTop1ByTitleOrderByIdDesc(title);
    }

    public List<Post> findByAuthorUsername(String username) {
        return postRepository.findByAuthorUsername(username);
    }
}
