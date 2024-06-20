package spring.board.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.board.domain.post.domain.Post;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDto {
  private Long postId;
  private String title;
  private String contents;
  private String thumbnailImageUrl;
  private Long memberId;

  public PostDto(Post post) {
    this.postId = post.getPostId();
    this.title = post.getTitle();
    this.contents = post.getContents();
    this.thumbnailImageUrl = post.getThumbnailImageUrl();
    this.memberId = post.getMember().getMemberId();
  }
}
