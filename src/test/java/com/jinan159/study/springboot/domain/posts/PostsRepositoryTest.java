package com.jinan159.study.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 단위 테스트가 끝날때 마다 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_save_and_select() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // insert / update 쿼리를 실행함 (id 있으면 update, 없으면 insert)
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("wlsdhks0423@naver.com")
                .build());

        // 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_test() {
        // given
        LocalDateTime now = LocalDateTime.of(2021,11,8,0,0);

        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        savedPosts.update("title2", "content2");
        postsRepository.save(savedPosts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>> CreateDate : " + posts.getCreateDate());
        System.out.println(">>>>> ModifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(posts.getCreateDate());
    }
}