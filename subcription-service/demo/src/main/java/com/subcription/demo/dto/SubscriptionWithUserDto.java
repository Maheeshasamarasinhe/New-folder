package com.subcription.demo.dto;

import lombok.Data;

@Data
public class SubscriptionWithUserDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private UserResponseDto user;
}
