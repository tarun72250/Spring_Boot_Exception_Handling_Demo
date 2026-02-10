package com.example.order_management.exception.constants;

/**
 * Centralized error codes - NO MAGIC STRINGS
 * 🔹 WHY? Never hardcode error codes, avoid typos, easy refactoring
 */
public final class ErrorCodes {
    
    // Business Error Codes
    public static final String ORDER_NOT_FOUND = "ORDER_NOT_FOUND";
    public static final String INVALID_ORDER_STATUS = "INVALID_ORDER_STATUS";
    public static final String DUPLICATE_ORDER = "DUPLICATE_ORDER";
    public static final String ORDER_PROCESSING_ERROR = "ORDER_PROCESSING_ERROR";
    
    // Validation Error Codes
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String MISSING_REQUIRED_FIELD = "MISSING_REQUIRED_FIELD";
    
    // System Error Codes
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String DATABASE_ERROR = "DATABASE_ERROR";
    public static final String NETWORK_ERROR = "NETWORK_ERROR";
    
    // Private constructor to prevent instantiation
    private ErrorCodes() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
