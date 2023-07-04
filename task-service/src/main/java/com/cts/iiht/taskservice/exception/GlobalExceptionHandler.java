package com.cts.iiht.taskservice.exception;

import feign.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });

        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException fx){

        Map<String, String> resp = new HashMap<>();
        resp.put("errorMessage",fx.getLocalizedMessage());
        resp.put("statusCode",HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<Map<String,String>> handleInvalidRequestException(DataValidationException ex){

        Map<String, String> resp = new HashMap<>();
        resp.put("errorMessage",ex.getMessage());
        resp.put("statusCode",HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
