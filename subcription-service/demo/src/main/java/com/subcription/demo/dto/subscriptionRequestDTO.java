package com.subcription.demo.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@Data


public class subscriptionRequestDTO {

    @NotBlank (message = "Name is mandatory")
    private String name;
    private String description;
    @NotBlank( message = "Duration is mandatory")
    private double price;


}
