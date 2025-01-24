package com.example.jpa.global.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass // 상속용 엔티티 (테이블 생성x)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseTime extends BaseEntity {
    @CreatedDate // JPA가 대신 추적 관리
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createDate;

    @LastModifiedDate // JPA가 대신 추적 관리
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedDate;
}
