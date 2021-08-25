package com.renmoney_ha.configurations;

import com.renmoney_ha.payloads.response.RestExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionsHandlers extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new RestExceptionResponse(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                String.format("Required parameter is missing [ %s ]", request.getParameterNames())
        ), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityEntityExists(EntityNotFoundException ex) {
        return new ResponseEntity<>(new RestExceptionResponse(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                "No Duplication Accepted"
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleElementNotFound(RuntimeException s) {
        return new ResponseEntity<>(new RestExceptionResponse(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                "Required Entity Not Found"+ s.getMessage()
        ), HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidation(EntityNotFoundException ex) {
        return new ResponseEntity<>(new RestExceptionResponse(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                "Data constraint violation"
        ), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.ok(errors);
    }


    
}
