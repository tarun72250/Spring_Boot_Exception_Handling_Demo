package com.example.order_management.exception;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ISOLATED TEST - No Spring context required
 * This test proves your exception handling works correctly
 */
public class IsolatedExceptionTest {

    @Test
    public void testOrderNotFoundExceptionHandling() {
        System.out.println("🧪 Testing OrderNotFoundException handling...");
        
        // Create the exception handler (no Spring needed)
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        
        // Create a custom exception
        OrderNotFoundException exception = new OrderNotFoundException("Order not found with id: 999");
        
        // Handle the exception
        var response = handler.handleOrderNotFound(exception);
        
        // Verify the response
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue(), "HTTP status should be 404");
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError, "ApiError should not be null");
        assertEquals(404, apiError.getStatus(), "ApiError status should be 404");
        assertEquals("Order not found with id: 999", apiError.getMessage(), "Error message should match");
        assertEquals(ErrorCode.ORDER_NOT_FOUND.name(), apiError.getErrorCode(), "Error code should match");
        assertNotNull(apiError.getTimestamp(), "Timestamp should not be null");
        
        System.out.println("✅ OrderNotFoundException test PASSED");
        System.out.println("   Response: " + apiError);
        System.out.println();
    }

    @Test
    public void testGenericExceptionHandling() {
        System.out.println("🧪 Testing Generic Exception handling...");
        
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Database connection failed");
        
        var response = handler.handleAllExceptions(exception);
        
        assertNotNull(response, "Response should not be null");
        assertEquals(500, response.getStatusCodeValue(), "HTTP status should be 500");
        
        ApiError apiError = response.getBody();
        assertNotNull(apiError, "ApiError should not be null");
        assertEquals(500, apiError.getStatus(), "ApiError status should be 500");
        assertEquals("Something went wrong. Please try again later", apiError.getMessage(), "Generic error message should match");
        assertEquals(ErrorCode.INTERNAL_ERROR.name(), apiError.getErrorCode(), "Error code should match");
        assertNotNull(apiError.getTimestamp(), "Timestamp should not be null");
        
        System.out.println("✅ Generic Exception test PASSED");
        System.out.println("   Response: " + apiError);
        System.out.println();
    }

    @Test
    public void testApiErrorCreation() {
        System.out.println("🧪 Testing ApiError creation...");
        
        ApiError apiError = new ApiError(400, "Validation failed", "VALIDATION_FAILED");
        
        assertEquals(400, apiError.getStatus(), "Status should be 400");
        assertEquals("Validation failed", apiError.getMessage(), "Message should match");
        assertEquals("VALIDATION_FAILED", apiError.getErrorCode(), "Error code should match");
        assertNotNull(apiError.getTimestamp(), "Timestamp should not be null");
        
        System.out.println("✅ ApiError creation test PASSED");
        System.out.println("   ApiError: " + apiError);
        System.out.println();
    }

    @Test
    public void testCustomExceptionCreation() {
        System.out.println("🧪 Testing Custom Exception creation...");
        
        OrderNotFoundException exception = new OrderNotFoundException("Test custom exception");
        
        assertNotNull(exception, "Exception should not be null");
        assertEquals("Test custom exception", exception.getMessage(), "Exception message should match");
        assertTrue(exception instanceof RuntimeException, "Should extend RuntimeException");
        
        System.out.println("✅ Custom Exception creation test PASSED");
        System.out.println("   Exception: " + exception.getClass().getSimpleName() + " - " + exception.getMessage());
        System.out.println();
    }
}
