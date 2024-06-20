package spring.board.domain.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.board.domain.post.dto.PostDto;
import spring.board.domain.post.service.PostService;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {
  private final PostService postService;

  //== CREATE START ==//
  @PostMapping("/create")
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    PostDto createdPost = postService.createPost(postDto.getTitle(),
        postDto.getContents(),
        postDto.getThumbnailImageUrl(),
        postDto.getMemberId());
    return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
  }
  //== CREATE END ==//


  //== READ START ==//
  @GetMapping("/{postId}")
  public ResponseEntity<PostDto> findPost(@PathVariable("postId") Long postId) {
    PostDto postDto = postService.findPostById(postId);
    return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
  }
  //== READ END ==//



  //== UPDATE START ==//


  //== UPDATE END ==//



}
