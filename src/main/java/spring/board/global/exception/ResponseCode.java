package spring.board.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ResponseCode {
  //== Success Codes ==//
  SUCCESS("000001", "정상적으로 처리되었습니다."),

  //== Error Codes ==//
  NO_POST("404001", "포스트가 존재하지 않습니다."),
  NO_MEMBER("404002", "회원이 존재하지 않습니다."),
  NO_COMMENT("404003", "댓글이 존재하지 않습니다.");

  private final String code;
  private final String message;
}
