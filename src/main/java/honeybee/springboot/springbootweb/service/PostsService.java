package honeybee.springboot.springbootweb.service;



import honeybee.springboot.springbootweb.dto.PostsListResponseDto;
import honeybee.springboot.springbootweb.dto.PostsResponseDto;
import honeybee.springboot.springbootweb.dto.PostsSaveRequestDto;
import honeybee.springboot.springbootweb.dto.PostsUpdateRequestDto;
import honeybee.springboot.springbootweb.repository.PostRepository;
import honeybee.springboot.springbootweb.vo.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id =" + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id =" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        // .map(posts -> new PostsListResponseDto(posts)) = .map(PostsListResponseDto::new)
    }
    @Transactional
    public void delete(Long id){
       Posts posts = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
       postRepository.delete(posts);
    }
}
