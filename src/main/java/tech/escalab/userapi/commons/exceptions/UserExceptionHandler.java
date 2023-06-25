package tech.escalab.userapi.commons.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestHandler(MethodArgumentNotValidException ex,
                                                    WebRequest request){

        ErrorHttp message = new ErrorHttp(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                DetailError.mapearError(ex.getFieldErrors()),
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> ConflictHandler(DataIntegrityViolationException ex,
                                                    WebRequest request){

        List<DetailError> detailsErrores = new ArrayList<>();
        DetailError detalle = new DetailError();
        detailsErrores.add(detalle);

        ErrorHttp message = new ErrorHttp(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                DetailError.mapKeyError(ex),
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}