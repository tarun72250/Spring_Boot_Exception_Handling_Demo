package com.example.order_management.exception.custom;

import com.example.order_management.exception.constants.ErrorCodes;
import com.example.order_management.exception.enums.ErrorType;
import com.example.order_management.exception.constants.ErrorMessages;

/**
 * Resource Not Found Exception - Standard Business Exception
 */
public class ResourceNotFoundException extends BusinessException {
    
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(ErrorCodes.ORDER_NOT_FOUND, 
              ErrorType.BUSINESS, 
              String.format(ErrorMessages.ORDER_NOT_FOUND, resourceName + " with id: " + resourceId));
    }
    
    public ResourceNotFoundException(String message) {
        super(ErrorCodes.ORDER_NOT_FOUND, ErrorType.BUSINESS, message);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(ErrorCodes.ORDER_NOT_FOUND, ErrorType.BUSINESS, message, cause);
    }
}
