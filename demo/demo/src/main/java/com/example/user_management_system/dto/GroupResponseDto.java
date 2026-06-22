package com.example.user_management_system.dto;

import lombok.Data;
import java.util.Set;

@Data
public class GroupResponseDto {

    private Long id;
    private String name;
    private String description;
    private Set<UserResponseDto> users;
}
