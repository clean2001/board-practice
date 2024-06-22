package spring.board.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.board.global.response.BaseResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {
  @ExceptionHandler(BaseException.class)
  protected ResponseEntity<BaseResponse> handleBaseException(BaseException e) {
    BaseResponse baseResponse = BaseResponse.builder()
        .code(e.getResponseCode().getCode())
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(baseResponse, e.getHttpStatus());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<BaseResponse> handleException(Exception e) {
    BaseResponse baseResponse = BaseResponse.builder()
        .code("400001")
        .message(e.getMessage())
        .build();

    return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
  }
}
