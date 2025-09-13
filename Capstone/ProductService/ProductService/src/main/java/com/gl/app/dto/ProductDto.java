package com.gl.app.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2,message="Product name should have minimum 2 characters")
    private String name;
    @NotEmpty(message = "Category cannot be empty")
    @NotBlank(message = "Category cannot be null")
    @Size(min=3,message="Product name should have minimum 2 characters")
    private String category;
    @NotNull(message="Price cannot be null")
    private Double price;
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be null")
    private String description;
}
