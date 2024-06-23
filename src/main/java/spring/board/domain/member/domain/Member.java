package spring.board.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.board.domain.post.domain.Post;
import spring.board.global.entity.BaseEntity;
import spring.board.global.entity.BaseTimeEntity;

@ToString
@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String email;

  private String nickname;

  private String password;

  @OneToMany(mappedBy = "member")
  private final List<Post> posts = new ArrayList<>();

  public Member(String email, String nickname, String password) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
  }
}
