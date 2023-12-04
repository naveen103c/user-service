package com.backend.microservices.userservice.exception;

import com.backend.microservices.userservice.dto.APIError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoUserPresentException.class,DobNotValidException.class})
    ResponseEntity<Object> NoUserPresentHandler(Exception exception, ServletWebRequest servletWebRequest){
        APIError apiError=new APIError(HttpStatus.NOT_FOUND, Arrays.asList(exception.getMessage()),LocalDateTime.now(),servletWebRequest.getDescription(true));
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList=ex.getBindingResult().getFieldErrors().stream().map(err->err.getField() + ":" +err.getDefaultMessage()).collect(Collectors.toList());
        APIError apiError=new APIError((HttpStatus) status,errorList, LocalDateTime.now(),request.getDescription(true));
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
