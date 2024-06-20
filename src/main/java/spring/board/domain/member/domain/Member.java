package spring.board.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.board.domain.post.domain.Post;
import spring.board.global.entity.BaseEntity;

@Getter
@Entity
public class Member extends BaseEntity {
  @Id
  @GeneratedValue
  private Long memberId;

  private String email;

  private String nickname;

  private String password;

  @OneToMany(mappedBy = "member")
  private final List<Post> posts = new ArrayList<>();
}
