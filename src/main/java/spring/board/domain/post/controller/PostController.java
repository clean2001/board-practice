package spring.board.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.board.domain.post.dto.PostDto;
import spring.board.domain.post.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostController {
  private final PostService postService;

  // c
  @PostMapping("/create")
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    PostDto createdPost = postService.createPost(postDto.getTitle(), postDto.getContents(), null,
        postDto.getMemberId());
    return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
  }

  // r

  // u

  // d

}
