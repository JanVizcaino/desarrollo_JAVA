package com.example_jvb.demo_jvb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private Double price;

    // Nombre de la categoría en lugar del id
    private String categoryName;
    // Getters y Setters
}
