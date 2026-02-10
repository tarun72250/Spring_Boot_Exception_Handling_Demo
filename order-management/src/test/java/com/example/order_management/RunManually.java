package com.example.order_management;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;

/**
 * Run this class manually to test exception handling without Maven
 */
public class RunManually {

    public static void main(String[] args) {
        System.out.println("=== Testing Exception Handling Manually ===\n");
        
        try {
            testOrderNotFoundException();
            testGenericException();
            testApiErrorCreation();
            
            System.out.println("🎉 ALL TESTS PASSED! Your exception handling is working correctly.");
            
        } catch (Exception e) {
            System.err.println("❌ TEST FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testOrderNotFoundException() {
        System.out.println("Testing OrderNotFoundException...");
        
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        OrderNotFoundException exception = new OrderNotFoundException("Order not found with id: 999");
        
        var response = handler.handleOrderNotFound(exception);
        
        // Verify response
        if (response.getStatusCodeValue() != 404) {
            throw new RuntimeException("Expected 404, got " + response.getStatusCodeValue());
        }
        
        ApiError apiError = response.getBody();
        if (apiError == null) {
            throw new RuntimeException("ApiError is null");
        }
        
        if (apiError.getStatus() != 404) {
            throw new RuntimeException("Expected status 404, got " + apiError.getStatus());
        }
        
        if (!apiError.getErrorCode().equals(ErrorCode.ORDER_NOT_FOUND.name())) {
            throw new RuntimeException("Wrong error code: " + apiError.getErrorCode());
        }
        
        System.out.println("✅ OrderNotFoundException test passed");
        System.out.println("   Response: " + apiError);
        System.out.println();
    }
    
    private static void testGenericException() {
        System.out.println("Testing Generic Exception...");
        
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Unexpected database error");
        
        var response = handler.handleAllExceptions(exception);
        
        // Verify response
        if (response.getStatusCodeValue() != 500) {
            throw new RuntimeException("Expected 500, got " + response.getStatusCodeValue());
        }
        
        ApiError apiError = response.getBody();
        if (apiError == null) {
            throw new RuntimeException("ApiError is null");
        }
        
        if (apiError.getStatus() != 500) {
            throw new RuntimeException("Expected status 500, got " + apiError.getStatus());
        }
        
        if (!apiError.getErrorCode().equals(ErrorCode.INTERNAL_ERROR.name())) {
            throw new RuntimeException("Wrong error code: " + apiError.getErrorCode());
        }
        
        System.out.println("✅ Generic Exception test passed");
        System.out.println("   Response: " + apiError);
        System.out.println();
    }
    
    private static void testApiErrorCreation() {
        System.out.println("Testing ApiError creation...");
        
        ApiError apiError = new ApiError(400, "Bad request", "VALIDATION_FAILED");
        
        if (apiError.getStatus() != 400) {
            throw new RuntimeException("Expected status 400, got " + apiError.getStatus());
        }
        
        if (!apiError.getMessage().equals("Bad request")) {
            throw new RuntimeException("Wrong message: " + apiError.getMessage());
        }
        
        if (apiError.getTimestamp() == null) {
            throw new RuntimeException("Timestamp is null");
        }
        
        System.out.println("✅ ApiError creation test passed");
        System.out.println("   ApiError: " + apiError);
        System.out.println();
    }
}
