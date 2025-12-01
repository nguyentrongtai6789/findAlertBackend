package com.pro.findAlertBackEnd.config.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        log.error("\n=============>>> IllegalArgumentException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Invalid argument", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("\n=============>>> MethodArgumentNotValidException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Validation Error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDatabaseError(DataAccessException ex, HttpServletRequest request) {
        log.error("\n=============>>> DataAccessException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Database error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNotFound(NoResourceFoundException ex, HttpServletRequest request) {
        log.error("\n=============>>> NoResourceFoundException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.error("\n=============>>> HttpRequestMethodNotSupportedException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.error("\n=============>>> MissingServletRequestParameterException <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Missing request parameter", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex, HttpServletRequest request) {
        log.error("\n=============>>> Unexpected exception <<<=============" + " API: {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);
        return new ResponseEntity<>("Unexpected error", HttpStatus.BAD_REQUEST);
    }
}
