package code.vanilson.startup.exception.handler;

import code.vanilson.startup.exception.IllegalRequestException;
import code.vanilson.startup.exception.ObjectExceptionResponse;
import code.vanilson.startup.exception.ObjectWithIdNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@ControllerAdvice
public class ObjectEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ObjectWithIdNotFound.class)
    public final ResponseEntity<ObjectExceptionResponse> handleNotFoundRequest(
            Exception e, WebRequest webRequest) {
        ObjectExceptionResponse objectExceptionResponse = new ObjectExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(objectExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalRequestException.class)
    public final ResponseEntity<ObjectExceptionResponse> handleBadRequest(
            Exception e, WebRequest webRequest) {
        ObjectExceptionResponse objectExceptionResponse = new ObjectExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(objectExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
