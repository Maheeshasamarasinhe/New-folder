package com.subcription.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class subscriptionRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    @NotNull(message = "Price is mandatory")
    private Double price;
    @NotNull(message = "User ID is mandatory")
    private Long userId;
}
