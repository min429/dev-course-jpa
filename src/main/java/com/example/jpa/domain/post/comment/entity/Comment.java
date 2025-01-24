package com.example.jpa.domain.post.comment.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // 추적관리(생성, 수정 등)가 필요한 엔티티임을 알림
public class Comment extends BaseTime {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Setter(AccessLevel.PRIVATE)
    private Long id; // JPA가 id가 null인지 아닌지 판단하는 게 중요

    @Column(columnDefinition = "TEXT") // ~ 약 63000자
    private String body;

    // mappedBy를 사용하지 않은 쪽이 연관관계의 주인 // DB 반영은 관계의 주인쪽에서 해야함
    @ManyToOne(fetch = FetchType.LAZY) // default: FetchType.EAGER
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
}
