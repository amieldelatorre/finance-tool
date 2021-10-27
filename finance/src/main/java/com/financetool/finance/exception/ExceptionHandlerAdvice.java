package com.financetool.finance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleException(BadRequestException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(new Error(new Date(), HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error(new Date(), HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage(), request.getRequestURI()));
    }
}
