package com.example.jpa.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.domain.member.entity.Member;
import com.example.jpa.domain.member.service.MemberService;
import com.example.jpa.domain.post.comment.entity.Comment;
import com.example.jpa.domain.post.comment.service.CommentService;
import com.example.jpa.domain.post.post.entity.Post;
import com.example.jpa.domain.post.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final CommentService commentService;

    // 프록시 객체를 획득
    @Autowired
    @Lazy
    private BaseInitData self; // 프록시
    @Autowired
    private MemberService memberService;

    @Bean
    @Order(1)
    public ApplicationRunner applicationRunner() {
        return args -> {
            // 데이터가 3개가 이미 있으면 패스
            if (postService.count() > 0) {
                return;
            }

            // // insert는 쿼리를 바로바로 보냄
            // System.out.println("==== 1번 데이터 생성 ====");
            // Post p1 = postService.write("title1", "body1");
            // System.out.println("==== 1번 데이터 생성 완료 ====");
            //
            // System.out.println("==== 2번 데이터 생성 ====");
            // postService.write("title2", "body2");
            // System.out.println("==== 2번 데이터 생성 완료 ====");
            //
            // System.out.println("==== 3번 데이터 생성 ====");
            // postService.write("title3", "body3");
            // System.out.println("==== 3번 데이터 생성 완료 ====");
            //
            // commentService.write(p1, "comment1");
            // commentService.write(p1, "comment2");
            // commentService.write(p1, "comment3");
        };
    }

    // @Bean
    // @Order(2)
    // public ApplicationRunner applicationRunner2() {
    //     // return args -> {
    //     //     Post post = postService.findById(1L).get(); // 메서드 실행 후 영속성 컨텍스트는 사라짐
    //     //
    //     //     Thread.sleep(1000);
    //     //
    //     //     // postService.modify(post, "new title", "new body");
    //     //     postService.modify2(1L, "new title", "new body");
    //     // };
    //
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) throws Exception {
    //                 Post post = postService.findById(1L).get(); // 메서드 실행 후 영속성 컨텍스트는 사라지지 않음
    //
    //                 Thread.sleep(1000);
    //
    //                 postService.modify(post, "new title", "new body");
    //         }
    //     };
    // }
    //
    // @Bean
    // @Order(3)
    // public ApplicationRunner applicationRunner3() {
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) {
    //             Post post1 = postService.findById(1L).get();
    //             Post post2 = postService.findById(2L).get();
    //
    //             // postService.delete(post1);
    //             // postService.delete(post2);
    //
    //             // update, delete는 쿼리를 바로 보내지 않고 트랜잭션이 끝날 때 한번에 보냄
    //             System.out.println("====== post1 삭제 ======");
    //             postService.deleteById(1L);
    //             System.out.println("====== post1 삭제 완료 ======");
    //
    //             System.out.println("====== post2 삭제 ======");
    //             postService.deleteById(2L);
    //             System.out.println("====== post2 삭제 완료======");
    //         }
    //     };
    // }
    //
    // @Bean
    // @Order(4)
    // public ApplicationRunner applicationRunner4() {
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) {
    //             Post post = postService.findById(3L).get();
    //             System.out.println(post.getId() + "번 포스트를 가져왔습니다.");
    //             System.out.println("======================================");
    //
    //             Post post2 = postService.findById(3L).get();
    //             System.out.println(post.getId() + "번 포스트를 가져왔습니다.");
    //         }
    //     };
    // }

    // =============================================================================================================== //

    // @Bean
    // @Order(5)
    // public ApplicationRunner applicationRunner5() {
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) {
    //             Post post = postService.findById(1L).get();
    //
    //             // 데이터가 3개가 이미 있으면 패스
    //             if (commentService.count() > 0) {
    //                 return;
    //             }
    //
    //             Post post = postService.findById(1L).get();
    //
    //             Comment c1 = commentService.write(post.getId(), "comment1");
    //             Comment c2 = commentService.write(post.getId(), "comment2");
    //             Comment c3 = commentService.write(post.getId(), "comment3");
    //
    //             // 1번 게시글에 대한 댓글
    //             System.out.println(c1.getId() + "번 댓글의 부모 게시글 번호는 " + c1.getPostId() + "입니다.");
    //
    //             // 1번 댓글이 달린 게시글의 정보
    //             Post parent = postService.findById(c1.getPostId()).get();
    //             System.out.println(c1.getId() + "번 댓글의 부모 게시글 제목은 " + parent.getTitle() + "입니다.");
    //
    //
    //             // 1번 방식 -> DB 친화적
    //             Comment c4 = commentService.write(post.getId(), "comment4");
    //
    //             Comment c5 = Comment.builder()
    //                 .body("comment5")
    //                 .build();
    //
    //             // 2번 방식 -> 객체 친화적
    //             post.addComment(c5);
    //
    //             // DB 친화적
    //             long parentId = c5.getPostId();
    //             Post parent = postService.findById(parentId).get();
    //         }
    //     };
    // }

    // @Bean
    // @Order(6)
    // public ApplicationRunner applicationRunner6() {
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) {
    //             // 즉시로딩 fetchType.EAGER
    //
    //             Comment c1 = commentService.findById(1L).get();
    //             // SELECT * FROM comment join post on comment.postId = post.id WHERE id = 1;
    //
    //             Post post = c1.getPost();
    //         }
    //     };
    // }

    // @Bean
    // @Order(7)
    // public ApplicationRunner applicationRunner7() {
    //     return new ApplicationRunner() {
    //         @Override
    //         @Transactional
    //         public void run(ApplicationArguments args) {
    //             // 지연로딩 fetchType.LAZY
    //
    //             Comment c1 = commentService.findById(1L).get();
    //             // SELECT * FROM comment WHERE id = 1;
    //
    //             Post post = c1.getPost();
    //
    //             System.out.println(post.getId()); // Post가 null은 아니고, id 하나만 들어있다.
    //
    //             System.out.println(post.getTitle());
    //             // SELECT * FROM post WHERE id = 1;
    //         }
    //     };
    // }

    @Bean
    @Order(8)
    public ApplicationRunner applicationRunner8() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                self.work1();
                self.work2();
            }
        };
    }

    // @Transactional
    // public void work() {
    //     // 시작
    //
    //     Comment c1 = commentService.findById(1L).get();
    //     // SELECT * FROM comment WHERE id = 1;
    //
    //     Post post = c1.getPost(); // EAGER -> 이미 모든 post정보를 위에서 join으로 가져옴.
    //     // LAZY -> post -> 비어 있다.
    //     System.out.println(post.getId()); // post가 null은 아니고. id 하나만 채워져 있다.
    //
    //     System.out.println(post.getTitle());
    //
    //
    //     // 끝
    // }

    // @Transactional
    // public void work1() {
    //     if (postService.count() > 0) {
    //         return;
    //     }
    //
    //     Post p1 = postService.write("title1", "body1");
    //
    //     Comment c1 = Comment.builder()
    //         .body("comment1")
    //         .build();
    //
    //     //        c1 = commentService.save(c1); -> 즉시 DB 반영 (쿼리 보냄) // 현재 DB Comment table에 c1만 존재
    //
    //     p1.addComment(c1);
    //
    //     Comment c2 = Comment.builder()
    //         .body("comment2")
    //         .build();
    //
    //     p1.addComment(c2);
    //
    //     Comment c3 = Comment.builder()
    //         .body("comment3")
    //         .build();
    //
    //     p1.addComment(c3);
    //     p1.removeComment(c1); // 이 시점에서 p1에 c2, c3만 있음 -> DB에 c2, c3 저장 // 트랜잭션 종료 후 DB Comment table에 c1, c2, c3 존재
    // }
    //
    // @Transactional // @Transactional 붙이지 않으면 영속성 컨텍스트가 닫혀 Lazy 로딩이 불가능함 // Lazy 로딩 -> 무조건 @Transactional 필요
    // public void work2() {
    //     Post post = postService.findById(1L).get();
    //     System.out.println("1번 포스트 가져옴");
    //
    //     // 쿼리 안 보냄 빈 리스트(프록시 x)
    //     //     List<Comment> comments = post.getComments();
    //     //     System.out.println("1번 포스트의 댓글 가져옴");
    //     //
    //     //     Comment comment3 = Comment.builder()
    //     //         .body("comment3")
    //     //         .build();
    //     //
    //     //     post.addComment(comment3);
    //     //
    //     //     // 한번에 모든 댓글 정보 가져옴.
    //     // select * from comment where id = 1 (x)
    //     // select * from comment where post_id = 1; (o)
    //     Comment c1 = comments.get(0);
    //     System.out.println("첫번째 댓글 가져옴");
    //
    //     // 2번째 댓글 가져옴. DB 조회 안함.
    //     // select * from comment where id = 2 (x)
    //     Comment c2 = comments.get(1);
    // }

    @Transactional
    public void work1() {
        if(memberService.count() > 0) {
            return;
        }

        // 회원 샘플데이터 생성
        memberService.join("시스템" ,"1234", "시스템");
        memberService.join("admin" ,"1234", "관리자");
        memberService.join("user1" ,"1234", "유저1");
        memberService.join("user2" ,"1234", "유저2");
        memberService.join("user3" ,"1234", "유저3");
    }

    @Transactional
    public void work2() {

        if (postService.count() > 0) {
            return;
        }

        Member user1 = memberService.findByUsername("user1").get();
        Member user2 = memberService.findByUsername("user2").get();
        Member user3 = memberService.findByUsername("user3").get();

        Post p1 = postService.write(user1, "title1", "body1");
        Post p2 = postService.write(user2, "title1", "body2");
        Post p3 = postService.write(user3, "title1", "body3");

        p1.addTag("JPA");
        p1.addTag("SpringBoot");
        p1.addTag("개발");
        p1.addTag("개발");

        Comment c1 = Comment.builder()
            .author(user1)
            .body("comment1")
            .build();

        p1.addComment(c1);

        Comment c2 = Comment.builder()
            .author(user1)
            .body("comment2")
            .build();

        p1.addComment(c2);

        Comment c3 = Comment.builder()
            .author(user2)
            .body("comment3")
            .build();

        p1.addComment(c3);
    }
}
