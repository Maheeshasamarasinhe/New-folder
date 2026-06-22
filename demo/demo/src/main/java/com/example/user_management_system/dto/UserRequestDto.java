package com.example.user_management_system.dto;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data

public class UserRequestDto
{

    @Id
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only digits")
    private String telephone;

}
