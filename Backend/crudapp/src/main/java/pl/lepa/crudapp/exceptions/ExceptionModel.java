package pl.lepa.crudapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ExceptionModel {

    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime time;

    public ExceptionModel(String message, HttpStatus httpStatus, ZonedDateTime time) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }
}
