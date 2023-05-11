package communication.chatgpt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChatGptException.class)
    public ResponseEntity<String> chatGptExceptionHandler(ChatGptException e) {
        log.error(e.getMessage());
        return error(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> chatGptExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return serverError();
    }

    private ResponseEntity<String> error(ChatGptException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpstatus()).body("실패");
    }

    private ResponseEntity<String> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패");
    }
}