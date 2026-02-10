package com.example.order_management.exception.custom;

import com.example.order_management.exception.constants.ErrorCodes;
import com.example.order_management.exception.enums.ErrorType;

/**
 * Base Business Exception - ENTERPRISE CORE
 * 🔹 WHY? All business exceptions behave the same, reusable logic
 */
public abstract class BusinessException extends RuntimeException {
    
    private final String errorCode;
    private final ErrorType errorType;
    
    protected BusinessException(String errorCode, ErrorType errorType, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
    
    protected BusinessException(String errorCode, ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public ErrorType getErrorType() {
        return errorType;
    }
}
