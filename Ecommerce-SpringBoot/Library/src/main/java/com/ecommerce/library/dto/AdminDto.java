package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {

    @Size(min = 3, max = 15, message = "Invalid first name!(3-15 characters)")
    private String firstName;

    @Size(min = 3, max = 10, message = "Invalid last name!(3-15 characters)")
    private String lastName;

    private String username;

    @Size(min = 5, max = 20, message = "Invalid password!(5-20 characters)")
    private String password;

    private String repeatPassword;

    private String email;
}
