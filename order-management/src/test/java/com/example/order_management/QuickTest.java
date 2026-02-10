package com.example.order_management;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;

/**
 * QUICK TEST - Run this directly to verify exception handling works
 * No Maven, no Spring context - just pure Java
 */
public class QuickTest {

    public static void main(String[] args) {
        System.out.println("🚀 QUICK EXCEPTION HANDLING TEST\n");
        
        int testsPassed = 0;
        int totalTests = 3;
        
        try {
            // Test 1: OrderNotFoundException
            if (testOrderNotFoundException()) testsPassed++;
            
            // Test 2: Generic Exception
            if (testGenericException()) testsPassed++;
            
            // Test 3: ApiError Creation
            if (testApiErrorCreation()) testsPassed++;
            
            System.out.println("📊 TEST RESULTS: " + testsPassed + "/" + totalTests + " tests passed");
            
            if (testsPassed == totalTests) {
                System.out.println("🎉 ALL TESTS PASSED! Your exception handling is working perfectly!");
            } else {
                System.out.println("❌ Some tests failed. Check the output above.");
            }
            
        } catch (Exception e) {
            System.err.println("💥 TEST CRASHED: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static boolean testOrderNotFoundException() {
        System.out.println("🧪 Test 1: OrderNotFoundException");
        
        try {
            GlobalExceptionHandler handler = new GlobalExceptionHandler();
            OrderNotFoundException exception = new OrderNotFoundException("Order not found with id: 999");
            
            var response = handler.handleOrderNotFound(exception);
            
            if (response.getStatusCodeValue() != 404) {
                System.out.println("   ❌ Expected 404, got " + response.getStatusCodeValue());
                return false;
            }
            
            ApiError apiError = response.getBody();
            if (apiError == null) {
                System.out.println("   ❌ ApiError is null");
                return false;
            }
            
            if (!apiError.getErrorCode().equals(ErrorCode.ORDER_NOT_FOUND.name())) {
                System.out.println("   ❌ Wrong error code: " + apiError.getErrorCode());
                return false;
            }
            
            System.out.println("   ✅ PASSED - " + apiError);
            return true;
            
        } catch (Exception e) {
            System.out.println("   ❌ FAILED - " + e.getMessage());
            return false;
        }
    }
    
    private static boolean testGenericException() {
        System.out.println("🧪 Test 2: Generic Exception");
        
        try {
            GlobalExceptionHandler handler = new GlobalExceptionHandler();
            RuntimeException exception = new RuntimeException("Unexpected error");
            
            var response = handler.handleAllExceptions(exception);
            
            if (response.getStatusCodeValue() != 500) {
                System.out.println("   ❌ Expected 500, got " + response.getStatusCodeValue());
                return false;
            }
            
            ApiError apiError = response.getBody();
            if (apiError == null) {
                System.out.println("   ❌ ApiError is null");
                return false;
            }
            
            if (!apiError.getErrorCode().equals(ErrorCode.INTERNAL_ERROR.name())) {
                System.out.println("   ❌ Wrong error code: " + apiError.getErrorCode());
                return false;
            }
            
            System.out.println("   ✅ PASSED - " + apiError);
            return true;
            
        } catch (Exception e) {
            System.out.println("   ❌ FAILED - " + e.getMessage());
            return false;
        }
    }
    
    private static boolean testApiErrorCreation() {
        System.out.println("🧪 Test 3: ApiError Creation");
        
        try {
            ApiError apiError = new ApiError(400, "Bad request", "VALIDATION_FAILED");
            
            if (apiError.getStatus() != 400) {
                System.out.println("   ❌ Expected status 400, got " + apiError.getStatus());
                return false;
            }
            
            if (apiError.getTimestamp() == null) {
                System.out.println("   ❌ Timestamp is null");
                return false;
            }
            
            System.out.println("   ✅ PASSED - " + apiError);
            return true;
            
        } catch (Exception e) {
            System.out.println("   ❌ FAILED - " + e.getMessage());
            return false;
        }
    }
}
