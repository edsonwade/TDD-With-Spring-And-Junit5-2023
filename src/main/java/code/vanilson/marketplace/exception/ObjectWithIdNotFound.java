package code.vanilson.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "object not founded")
public class ObjectWithIdNotFound extends RuntimeException {
    public ObjectWithIdNotFound(String message) {
        super(message);
    }
}
