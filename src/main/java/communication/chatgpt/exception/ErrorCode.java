package communication.chatgpt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INPUT_NOT_FOUND(HttpStatus.NOT_FOUND, "입력값이 공백입니다. 다시 입력해 주세요."),
    BODY_NOT_FOUND(HttpStatus.NOT_FOUND, "Body 값이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

    private final HttpStatus httpstatus;
    private final String message;
}
