package com.subcription.demo.dto;

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
