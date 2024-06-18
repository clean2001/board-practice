package spring.board.domain.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.board.domain.member.domain.Member;
import spring.board.domain.post.domain.Post;

@Getter
@Entity
public class Comment {
  @Id
  @GeneratedValue
  private Long commentId;

  private String contents;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Post post;

}
