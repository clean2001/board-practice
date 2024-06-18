package spring.board.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import spring.board.domain.post.domain.Post;

@Getter
@Entity
public class Member {
  @Id
  @GeneratedValue
  private Long memberId;

  private String email;

  private String nickname;

  private String password;

  @OneToMany(mappedBy = "member")
  private List<Post> posts = new ArrayList<>();
}
