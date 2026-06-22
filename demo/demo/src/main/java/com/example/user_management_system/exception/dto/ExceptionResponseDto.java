package com.example.user_management_system.exception.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ExceptionResponseDto {
    private String message;
    private int code;
    private Map<String, Object> data;
}
