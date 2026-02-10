package com.example.order_management;

import com.example.order_management.exception.custom.OrderNotFoundException;
import com.example.order_management.exception.global.GlobalExceptionHandler;
import com.example.order_management.exception.model.ApiError;
import com.example.order_management.enums.ErrorCode;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Manual test runner to verify exception handling without complex Spring Test setup
 */
public class ManualTestRunner {

    public static void main(String[] args) {
        System.out.println("=== Testing Exception Handling ===\n");
        
        // Test 1: OrderNotFoundException
        testOrderNotFoundException();
        
        // Test 2: Generic Exception
        testGenericException();
        
        // Test 3: Application Context (optional)
        testApplicationContext();
        
        System.out.println("=== All tests completed ===");
    }
    
    private static void testOrderNotFoundException() {
        System.out.println("Test 1: OrderNotFoundException");
        
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        OrderNotFoundException exception = new OrderNotFoundException("Order not found with id: 999");
        
        ResponseEntity<ApiError> response = handler.handleOrderNotFound(exception);
        
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody());
        
        // Assertions
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
        assert response.getBody() != null;
        assert response.getBody().getStatus() == 404;
        assert response.getBody().getErrorCode().equals(ErrorCode.ORDER_NOT_FOUND.name());
        
        System.out.println("✅ OrderNotFoundException test passed\n");
    }
    
    private static void testGenericException() {
        System.out.println("Test 2: Generic Exception");
        
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Unexpected error");
        
        ResponseEntity<ApiError> response = handler.handleAllExceptions(exception);
        
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody());
        
        // Assertions
        assert response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR;
        assert response.getBody() != null;
        assert response.getBody().getStatus() == 500;
        assert response.getBody().getErrorCode().equals(ErrorCode.INTERNAL_ERROR.name());
        
        System.out.println("✅ Generic Exception test passed\n");
    }
    
    private static void testApplicationContext() {
        System.out.println("Test 3: Application Context");
        
        try {
            ConfigurableApplicationContext context = SpringApplication.run(OrderManagementApplication.class);
            System.out.println("✅ Application context loaded successfully");
            context.close();
        } catch (Exception e) {
            System.out.println("❌ Application context failed: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
    }
}
