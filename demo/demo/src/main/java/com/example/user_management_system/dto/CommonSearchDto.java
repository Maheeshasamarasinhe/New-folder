package com.example.user_management_system.dto;

import lombok.Data;
import java.util.Map;

@Data
public class CommonSearchDto {
    private Map<String, String> searchCriteria;
}
