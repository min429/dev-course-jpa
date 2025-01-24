package com.example.jpa;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.jpa.domain.post.post.service.Post;

@SpringBootTest
@ActiveProfiles("test")
class JpaApplicationTests {

	@Test
	void t1() {
		Post p1 = new Post(1, "title1");
		Post p2 = new Post(1, "title1");

		assertThat(p1).isEqualTo(p2);

		// equals() 메서드는 재정의하지 않으면 Object 기본 equals를 사용 -> 참조 값이 다르면 무조건 다르다고 봄
		// equals()를 재정의해줘야 필드끼리 비교함

		//		assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
	}

	@Test
	void t2() {
		Post p1 = new Post(1, "title1");
		Post p2 = new Post(1, "title2");

		Set<Post> posts = new HashSet<>(); // 중복 허용 X

		posts.add(p1);
		posts.add(p2);

		assertThat(posts.size()).isEqualTo(1);

		//		assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
	}
}
