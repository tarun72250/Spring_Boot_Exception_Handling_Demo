package com.example.order_management.exception.model;

import com.example.order_management.exception.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * STANDARD ERROR RESPONSE MODEL
 * 🔹 WHY? Client should always get the same response structure
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private int status;
    private String errorCode;
    private String message;
    private ErrorType errorType;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(int status, String errorCode, String message, ErrorType errorType) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(int status, String errorCode, String message, ErrorType errorType, String path) {
        this(status, errorCode, message, errorType);
        this.path = path;
    }

    // Getters
    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters (if needed for frameworks)
    public void setStatus(int status) {
        this.status = status;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", errorType=" + errorType +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
