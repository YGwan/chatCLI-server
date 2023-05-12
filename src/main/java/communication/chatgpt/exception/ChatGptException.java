package communication.chatgpt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatGptException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public ChatGptException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            message = errorCode.getMessage();
        }
        return message;
    }
}
