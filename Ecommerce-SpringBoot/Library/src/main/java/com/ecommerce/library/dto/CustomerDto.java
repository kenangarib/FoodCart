package com.ecommerce.library.dto;

import com.ecommerce.library.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerDto {
    private Long id;
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String username;
    private String email;

    @Size(min = 5, max = 20, message = "Password should have 5-20 characters")
    private String password;
    private String repeatPassword;

    @Size(min = 10, max = 15, message = "Phone number contains 10-15 characters")
    private String phoneNumber;

    private String address;
    private City city;
    private String image;
    private String country;


}
