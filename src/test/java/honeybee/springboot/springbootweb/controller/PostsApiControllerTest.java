package honeybee.springboot.springbootweb.controller;


import honeybee.springboot.springbootweb.dto.PostsSaveRequestDto;
import honeybee.springboot.springbootweb.dto.PostsUpdateRequestDto;
import honeybee.springboot.springbootweb.repository.PostRepository;
import honeybee.springboot.springbootweb.vo.Posts;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postsRepository;

    @After
    public void tearDown1() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Postsregister_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Long savePostId = responseEntity.getBody();
        assertThat(savePostId).isNotNull();
        Optional<Posts> savePostOptional = postsRepository.findById(savePostId);
        assertThat(savePostOptional).isPresent();
        Posts savePost = savePostOptional.get();
        assertThat(savePost.getTitle()).isEqualTo(title);
        assertThat(savePost.getContent()).isEqualTo(content);
    }

    @Test
    public void Postsupdate_수정된다() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        // 수정된 코드
        Long savePostId = responseEntity.getBody();

        assertThat(savePostId).isNotNull();
        Optional<Posts> savePostOptional = postsRepository.findById(savePostId);
        assertThat(savePostOptional).isPresent();
        Posts savePost = savePostOptional.get();
        assertThat(savePost.getTitle()).isEqualTo(expectedTitle);
        assertThat(savePost.getContent()).isEqualTo(expectedContent);

//
//        Posts updatePosts = postsRepository.findById(updateId).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
//
//        assertThat(updatePosts.getTitle()).isEqualTo(expectedTitle);
//        assertThat(updatePosts.getContent()).isEqualTo(expectedContent);
    }
}