package com.example.jpa.global.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass // 상속용 엔티티 (테이블 생성x)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // @EqualsAndHashCode.Include로 명시적으로 지정한 필드만 equals와 hashCode 메서드에 포함
public class BaseEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Setter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Include
    private Long id; // JPA가 id가 null인지 아닌지 판단하는 게 중요
}
