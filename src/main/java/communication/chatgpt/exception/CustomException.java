package communication.chatgpt.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
