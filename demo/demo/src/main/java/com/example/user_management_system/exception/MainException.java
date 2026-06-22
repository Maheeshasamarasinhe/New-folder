package com.example.user_management_system.exception;

import com.example.user_management_system.exception.dto.ExceptionResponseDto;

import java.util.Map;

public  class MainException extends RuntimeException {
    private  String message;
    protected Map<String, Object> data;

    public MainException(String message, Map<String, Object> data) {
        super(message);
        this.message = message;
        this.data = data;
    }

    public ExceptionResponseDto getExceptionResponseDto() {
        return ExceptionResponseDto.builder()
                .message(this.message)
                .data(this.data)
                .build();
    }
}

