package com.dao.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DAOException extends RuntimeException {
    public DAOException(String message) {
        super(message);
    }
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
