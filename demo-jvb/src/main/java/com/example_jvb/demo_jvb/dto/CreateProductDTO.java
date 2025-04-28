package com.example_jvb.demo_jvb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String name;
    private double price;
    private Integer stock;
    private Double fabricationCost;
    private int categoryId;
}
