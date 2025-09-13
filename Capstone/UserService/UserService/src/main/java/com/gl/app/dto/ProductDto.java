package com.gl.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Category cannot be null")
    private String category;
    @Positive(message = " must be positive")
    private double price;
    @NotBlank(message = "Category cannot be null")
    private String description;


}
