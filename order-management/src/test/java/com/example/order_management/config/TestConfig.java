package com.example.order_management.config;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test configuration to exclude problematic auto-configurations
 */
@Configuration
@WebMvcTest
public class TestConfig {
    // This configuration helps exclude database-related auto-configurations
    // that might be causing context loading issues during tests
}
