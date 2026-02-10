package com.example.order_management.exception.constants;

/**
 * Centralized error messages - AVOID REPEATED STRINGS
 * 🔹 WHY? Avoid repeated strings, easy localization later
 */
public final class ErrorMessages {
    
    // Business Error Messages
    public static final String ORDER_NOT_FOUND = "Order not found with id: %s";
    public static final String INVALID_ORDER_STATUS = "Invalid order status: %s";
    public static final String DUPLICATE_ORDER = "Order already exists with id: %s";
    public static final String ORDER_PROCESSING_ERROR = "Error processing order: %s";
    
    // Validation Error Messages
    public static final String VALIDATION_FAILED = "Validation failed: %s";
    public static final String INVALID_INPUT = "Invalid input provided: %s";
    public static final String MISSING_REQUIRED_FIELD = "Required field is missing: %s";
    
    // System Error Messages
    public static final String INTERNAL_SERVER_ERROR = "An internal server error occurred. Please try again later.";
    public static final String DATABASE_ERROR = "Database operation failed. Please contact support.";
    public static final String NETWORK_ERROR = "Network operation failed. Please check your connection.";
    
    // Private constructor to prevent instantiation
    private ErrorMessages() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
