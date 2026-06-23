package com.subcription.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription")
public class subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    private String description;
    private Double price;
    private Long userId;



}
