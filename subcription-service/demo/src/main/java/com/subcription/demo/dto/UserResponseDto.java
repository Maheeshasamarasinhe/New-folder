package com.subcription.demo.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String telephone;
    private boolean status;
}
