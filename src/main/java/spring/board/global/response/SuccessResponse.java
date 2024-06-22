package spring.board.global.response;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SuccessResponse extends BaseResponse {
  private Object data;
}
