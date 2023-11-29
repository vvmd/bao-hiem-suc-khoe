package com.fit.health_insurance.security.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fit.health_insurance.security.exception.EmailExistedException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BadRequestHandler {
    @ExceptionHandler(EmailExistedException.class)
    public ResponseEntity<?> handle(RuntimeException ex, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        result.put("errors", ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}
