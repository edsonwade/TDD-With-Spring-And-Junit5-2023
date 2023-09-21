package code.vanilson.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "")
public class ObjectWithIdNotFound extends RuntimeException{
    public ObjectWithIdNotFound(String message) {
        super(message);
    }
}
