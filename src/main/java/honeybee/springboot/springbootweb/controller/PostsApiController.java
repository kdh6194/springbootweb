package honeybee.springboot.springbootweb.controller;

import honeybee.springboot.springbootweb.dto.PostsResponseDto;
import honeybee.springboot.springbootweb.dto.PostsSaveRequestDto;
import honeybee.springboot.springbootweb.dto.PostsUpdateRequestDto;
import honeybee.springboot.springbootweb.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
