package communication.chatgpt.exception;

import org.springframework.http.HttpStatus;

public class RequestDataNotFoundException extends CustomException {
    RequestDataNotFoundException(){
        super(HttpStatus.BAD_REQUEST, ErrorCode.INPUT_NOT_FOUND.getMessage());
    }
}
