package com.example.order_management.exception.custom;

import com.example.order_management.exception.constants.ErrorCodes;
import com.example.order_management.exception.enums.ErrorType;
import com.example.order_management.exception.constants.ErrorMessages;

/**
 * Validation Exception - Standard Validation Error
 */
public class ValidationException extends BusinessException {
    
    public ValidationException(String field, String constraint) {
        super(ErrorCodes.VALIDATION_FAILED, 
              ErrorType.VALIDATION, 
              String.format(ErrorMessages.VALIDATION_FAILED, field + " " + constraint));
    }
    
    public ValidationException(String message) {
        super(ErrorCodes.VALIDATION_FAILED, ErrorType.VALIDATION, message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(ErrorCodes.VALIDATION_FAILED, ErrorType.VALIDATION, message, cause);
    }
}
