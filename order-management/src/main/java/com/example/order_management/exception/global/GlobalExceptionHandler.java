package com.example.order_management.exception.global;

import com.example.order_management.exception.custom.BusinessException;
import com.example.order_management.exception.custom.ValidationException;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.exception.constants.ErrorCodes;
import com.example.order_management.exception.constants.ErrorMessages;
import com.example.order_management.exception.enums.ErrorType;
import com.example.order_management.exception.model.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

/**
 * GLOBAL EXCEPTION HANDLER - ENTERPRISE STANDARD
 * 
 * 🔹 FEATURES:
 * - Consistent error response
 * - No hard-coded values  
 * - Easy maintenance
 * - Easy client integration
 * - Clean logs
 * - Scalable for large projects
 * 
 * 🔹 STANDARD FLOW:
 * Client → Controller → Service → Custom Exception → GlobalExceptionHandler → ApiError → Client
 * 
 * 🟢 No try-catch in controller
 * 🟢 No hardcoded values
 * 🟢 Clean logs
 * 🟢 Consistent response
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * Handle all business exceptions
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(
            BusinessException ex, 
            HttpServletRequest request) {
        
        log.warn("Business exception occurred: {} - {}", ex.getErrorCode(), ex.getMessage());
        
        HttpStatus status = determineHttpStatus(ex.getErrorType());
        ApiError apiError = new ApiError(
                status.value(),
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getErrorType(),
                request.getRequestURI()
        );
        
        return ResponseEntity.status(status).body(apiError);
    }

    /**
     * Handle validation exceptions from @Valid annotations
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Validation failed");
        
        log.warn("Validation exception occurred: {}", errorMessage);
        
        ApiError apiError = new ApiError(
                AppConstants.BAD_REQUEST,
                ErrorCodes.VALIDATION_FAILED,
                errorMessage,
                ErrorType.VALIDATION,
                request.getRequestURI()
        );
        
        return ResponseEntity.badRequest().body(apiError);
    }

    /**
     * Handle all other exceptions (fallback)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(
            Exception ex, 
            HttpServletRequest request) {
        
        log.error("Unexpected error occurred at {}: {}", 
                request.getRequestURI(), 
                ex.getMessage(), 
                ex);
        
        ApiError apiError = new ApiError(
                AppConstants.INTERNAL_ERROR,
                ErrorCodes.INTERNAL_SERVER_ERROR,
                ErrorMessages.INTERNAL_SERVER_ERROR,
                ErrorType.SYSTEM,
                request.getRequestURI()
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    /**
     * Determine HTTP status based on error type
     */
    private HttpStatus determineHttpStatus(ErrorType errorType) {
        switch (errorType) {
            case BUSINESS:
                return HttpStatus.NOT_FOUND; // Most business errors are 404
            case VALIDATION:
                return HttpStatus.BAD_REQUEST;
            case SYSTEM:
                return HttpStatus.INTERNAL_SERVER_ERROR;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
