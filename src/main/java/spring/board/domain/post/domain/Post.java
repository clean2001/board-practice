package spring.board.domain.post.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import spring.board.domain.comment.domain.Comment;
import spring.board.domain.member.domain.Member;

@Getter
@Entity
public class Post {
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

}
