package spring.board.global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BaseResponse {
  protected String code;
  protected String message;
}