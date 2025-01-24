package com.example.jpa.domain.post.post.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // @EqualsAndHashCode.Include로 명시적으로 지정한 필드만 equals와 hashCode 메서드에 포함
public class Post {

    @EqualsAndHashCode.Include
    private long id;

    private String title;
}
