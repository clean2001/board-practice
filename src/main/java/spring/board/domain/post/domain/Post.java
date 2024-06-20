package spring.board.domain.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.board.domain.comment.domain.Comment;
import spring.board.domain.member.domain.Member;
import spring.board.global.entity.BaseEntity;
import spring.board.global.entity.BaseTimeEntity;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {
  @Id
  @GeneratedValue
  private Long postId;

  private String title;

  private String contents;

  private String thumbnailImageUrl;

  @ManyToOne
  private Member member;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  public Post(String title, String contents, String thumbnailImageUrl, Member member) {
    this.title = title;
    this.contents = contents;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.member = member;
  }

}


