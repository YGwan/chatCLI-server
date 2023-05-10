package communication.chatgpt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INPUT_NOT_FOUND(HttpStatus.NOT_FOUND, "입력값이 공백입니다. 다시 입력해 주세요.");

    private final HttpStatus httpstatus;
    private final String message;

}
