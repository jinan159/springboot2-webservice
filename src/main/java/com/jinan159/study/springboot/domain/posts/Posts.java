package com.jinan159.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // Getter 생성
@NoArgsConstructor // 인수가 없는 생성자를 생성
@Entity // 테이블과 링크될 클래스임을 나타냄(기본으로 카멜케이스를 언더스코어네이밍으로 매칭함, MyPosts => my_posts)
public class Posts {

    @Id // 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 규칙, 스프링부트 2.0 에서는 괄호안의 설정 있어야 auto_increment 됨
    private Long id;

    @Column(length = 500, nullable = false) // Entity의 속성은 기본적으로 모두 Column이 됨
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용됨
    private String content;

    private String author;

    @Builder // Builder 클래스를 자동 생성함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
