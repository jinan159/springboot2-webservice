package com.jinan159.study.springboot.service.posts;

import com.jinan159.study.springboot.web.dto.PostsResponseDto;
import com.jinan159.study.springboot.web.dto.PostsSaveRequestDto;
import com.jinan159.study.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// TODO : mock test 적용해서, 타 테스트에 지장없도록 개선해야함
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Test
    public void Insert_Update_Select_Test() {
        Long savedPostsId = postsService.save(PostsSaveRequestDto.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        PostsResponseDto resposeDto = postsService.findById(savedPostsId);
        String expectedTitle = "title2";
        String expectedContent = "content2";

        Long updatedId = postsService.update(resposeDto.getId(), PostsUpdateRequestDto.builder()
                .id(resposeDto.getId())
                .title(expectedTitle)
                .content(expectedContent)
                .build());

        PostsResponseDto responseDto = postsService.findById(updatedId);

        assertThat(responseDto.getTitle()).isEqualTo(expectedTitle);
        assertThat(responseDto.getContent()).isEqualTo(expectedContent);
    }
}
