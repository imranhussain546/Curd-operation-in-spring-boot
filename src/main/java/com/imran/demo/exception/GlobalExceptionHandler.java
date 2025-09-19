package com.imran.demo.exception;

import com.imran.demo.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                "An unexpected error occurred",
                "INTERNAL_SERVER_ERROR",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({
            IllegalArgumentException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class
    })
    public ResponseEntity<ApiErrorResponse> handleBadRequest(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                "Bad Request",
                "BAD_REQUEST",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomBadRequest(BadRequestException ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                "Validation failed",
                "BAD_REQUEST",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex) {
//        ApiErrorResponse error = new ApiErrorResponse(
//                "Resource not found",
//                "NOT_FOUND",
//                ex.getMessage()
//        );
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    // Add more specific handlers as needed
}

