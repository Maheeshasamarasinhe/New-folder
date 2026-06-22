package com.example.user_management_system.exception;

import java.util.Map;

public class DataValidationException extends MainException {
    public DataValidationException(String message, Map<String, Object> data) {
        super(message, data);
    }
}

