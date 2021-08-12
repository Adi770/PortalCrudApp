package pl.lepa.crudapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lepa.crudapp.model.ExceptionModel;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = {IncorrectJWT.class,InvalidUser.class})
    public ResponseEntity<Object> incorrectData(RuntimeException e){
        return getObjectResponseEntity(e,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            CommentNotFound.class,
            NewsNotFound.class,
            TokenNotFoundException.class,
            UsernameNotFoundException.class})
    public ResponseEntity<Object> notFound(RuntimeException e){
        return getObjectResponseEntity(e,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<Object> expired(RuntimeException e){
        return getObjectResponseEntity(e,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity<Object> exist(RuntimeException e){
        return getObjectResponseEntity(e,HttpStatus.CONFLICT);
    }


    private ResponseEntity<Object> getObjectResponseEntity(RuntimeException e, HttpStatus httpStatus)
    {

        ExceptionModel exception= new ExceptionModel(e.getMessage(),httpStatus,ZonedDateTime.now());
        return new ResponseEntity<>(exception,httpStatus);

    }
}
