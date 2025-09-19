package com.imran.demo.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class ApiErrorResponse {
    private String status = "error";
    private String message;
    private String errorCode;
    private String details;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiErrorResponse(String message, String errorCode, String details) {
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
    }

    // Getters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public String getErrorCode() { return errorCode; }
    public String getDetails() { return details; }
    public LocalDateTime getTimestamp() { return timestamp; }
}


