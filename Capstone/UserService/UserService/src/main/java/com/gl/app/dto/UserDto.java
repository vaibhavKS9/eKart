package com.gl.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
        @NotBlank(message = "Name cannot be null")
    @NotEmpty(message="Name cannot be empty")
    @Size(min=6,message = "Password should have minimum 6 characters")
    private String username;
    @NotBlank(message = "Email cannot be null")
    @Email(message ="Please provide a valid email")
    private String email;
    @Size(min=6,message = "Password should have minimum 6 characters")
    @NotBlank(message = "Password cannot be null")
    @NotEmpty(message="Name cannot be empty")
    private String password;

    List<ProductDto> products;


}
