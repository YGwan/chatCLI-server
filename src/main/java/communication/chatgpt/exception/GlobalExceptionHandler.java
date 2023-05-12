package communication.chatgpt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChatGptException.class)
    public ResponseEntity<String> chatGptExceptionHandler(ChatGptException e) {
        log.error(e.getMessage());
        return error(e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> hmnrExceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return error(new ChatGptException(ErrorCode.INPUT_NOT_FOUND));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> msrpExceptionHandler(MissingServletRequestPartException e) {
        log.error(e.getMessage());
        return error(new ChatGptException(ErrorCode.INPUT_NOT_FOUND));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> chatGptExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return serverError();
    }

    private ResponseEntity<String> error(ChatGptException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpstatus()).body(e.getMessage());
    }

    private ResponseEntity<String> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
    }
}
