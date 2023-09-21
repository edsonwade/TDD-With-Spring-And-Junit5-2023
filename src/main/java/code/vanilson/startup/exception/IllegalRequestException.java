package code.vanilson.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "")
public class IllegalRequestException extends RuntimeException{
    public IllegalRequestException(String message) {
        super(message);
    }
}



