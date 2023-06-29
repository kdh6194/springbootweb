package honeybee.springboot.springbootweb.controller;

import honeybee.springboot.springbootweb.repository.PostRepository;
import honeybee.springboot.springbootweb.vo.Posts;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup() {
        postRepository.deleteAll();
    }
    @Test
    public void list(){
        String title = "테스트게시글";
        String content = "테스트본문";

        postRepository.save(Posts.builder().title(title).content(content).author("abc123@naver.com").build());

        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
    @Test
    public void BaseTimeEntity_register(){
        LocalDateTime now = LocalDateTime.of(2023,6,29,11,42,30);
        postRepository.save(Posts.builder().title("title").content("content").author("author").build());
        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(10);

        System.out.println("createdDate="+posts.getCreatedDate()+",modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
