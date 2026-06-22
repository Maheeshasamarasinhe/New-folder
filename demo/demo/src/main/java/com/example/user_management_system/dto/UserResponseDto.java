package com.example.user_management_system.dto;


import lombok.Data;
@Data
public class UserResponseDto

{

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String telephone;
    private boolean status=true;



}
