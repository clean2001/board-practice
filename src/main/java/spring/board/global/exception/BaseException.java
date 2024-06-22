package spring.board.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
  private ResponseCode responseCode;
  private HttpStatus httpStatus;
}
