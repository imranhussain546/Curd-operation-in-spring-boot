package com.imran.demo.dto;

import java.util.Collection;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;


    public ApiResponse(String message, T data) {
        this.status = "success";
        this.message = message;
        this.data = data;

    }

    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }


    // Setters (optional)
}
