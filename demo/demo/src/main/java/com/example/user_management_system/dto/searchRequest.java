package com.example.user_management_system.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class searchRequest {

    @Size(max=255, message = "name can not be exceeded 255 characters.")

    private String username;



    @Size(max=255, message = "name can not be exceeded 255 characters.")


    private String role;

}
