package com.config.exception;

/**
 * Exception thrown when there are issues with database connection configuration or initialization.
 */
public class DatabaseConnectionException extends Exception {
    
    public DatabaseConnectionException(String message) {
        super(message);
    }
    
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
} 