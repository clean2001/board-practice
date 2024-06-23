package spring.board.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberDto {
  @JsonProperty(defaultValue = "0", required = false)
  private Long memberId;
  @Email
  private String email;

  @NotEmpty
  private String password;

  @NotEmpty
  private String nickname;
}
