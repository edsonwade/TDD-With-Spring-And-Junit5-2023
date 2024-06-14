package code.vanilson.startup.exception;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class ObjectExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final ZonedDateTime timestamp;
    private String details;

    public ObjectExceptionResponse(String message, ZonedDateTime timestamp, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }
}