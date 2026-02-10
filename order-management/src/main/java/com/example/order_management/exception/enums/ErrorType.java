package com.example.order_management.exception.enums;

/**
 * Error types for client understanding
 * 🔹 WHY? Client should know what type of error occurred
 */
public enum ErrorType {
    BUSINESS,      // Business logic violations (e.g., order not found)
    VALIDATION,    // Input validation failures
    SYSTEM         // Internal system errors
}
