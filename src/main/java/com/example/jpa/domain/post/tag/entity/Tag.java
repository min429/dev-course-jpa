package com.example.jpa.domain.post.tag.entity;

import com.example.jpa.domain.post.post.entity.Post;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {

    @EmbeddedId
    private TagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Include
    @MapsId("postId") // 외래 키를 TagId의 postId로 사용
    private Post post;
}
