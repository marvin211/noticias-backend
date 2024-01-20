package com.devmarvin.noticias.exceptions;

import com.devmarvin.noticias.utils.ErrorMessages;
import com.devmarvin.noticias.utils.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleValidationErrorException(MethodArgumentNotValidException ex, WebRequest webRequest){

        Map<String, String> errors = new HashMap<>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        ValidationErrors validationErrors = new ValidationErrors(errors, new Date());

        return new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { BadCredentialsException.class })
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest webRequest){
        ErrorMessages errorMessages = new ErrorMessages(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { ForbiddenException.class })
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest webRequest){
        ErrorMessages errorMessages = new ErrorMessages(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest webRequest){

        ErrorMessages errorMessages = new ErrorMessages(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
