package spring.board.domain.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.board.domain.member.domain.Member;
import spring.board.domain.post.domain.Post;
import spring.board.global.entity.BaseEntity;
import spring.board.global.entity.BaseTimeEntity;

@Getter
@Entity
public class Comment extends BaseTimeEntity {
  @Id
  @GeneratedValue
  private Long commentId;

  private String contents;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Post post;

}
