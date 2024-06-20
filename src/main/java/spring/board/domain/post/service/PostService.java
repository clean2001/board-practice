package spring.board.domain.post.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.board.domain.member.domain.Member;
import spring.board.domain.member.repository.MemberRepository;
import spring.board.domain.post.domain.Post;
import spring.board.domain.post.dto.PostDto;
import spring.board.domain.post.repository.PostRepository;

@AllArgsConstructor
@Service
public class PostService {
  private final MemberRepository memberRepository;
  private final PostRepository postRepository;

  // create
  public PostDto createPost(String title, String contents, String thumbnailImageUrl, Long memberId) {
    Optional<Member> memberOpt = memberRepository.findById(memberId);
    Member member = memberOpt.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다."));
    Post savedPost = postRepository.save(new Post(title, contents, thumbnailImageUrl, member));

    return new PostDto(savedPost.getPostId(),
        savedPost.getTitle(),
        savedPost.getContents(),
        savedPost.getThumbnailImageUrl(),
        savedPost.getMember().getMemberId());
  }

  // read
  public PostDto findPostById(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("포스트가 존재하지 않습니다"));

    return new PostDto(post.getPostId(),
        post.getTitle(),
        post.getContents(),
        post.getThumbnailImageUrl(),
        post.getMember().getMemberId());
  }

  //

}