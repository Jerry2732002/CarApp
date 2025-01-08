package com.example.CarApp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Data
public class Car {

    private int id;


    @NotBlank(message = "Name cannot be null")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Name should be alpha-numeric")
    private String name;


    @NotBlank(message = "Email cannot be null")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "phoneNo cannot be null")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    @Pattern(regexp = "[1-9][0-9]{9}", message = "Invalid Phone No.")
    private String phoneNo;


    @NotBlank(message = "Address cannot be null")
    @Size(min = 3, max = 100, message = "Name should be between 3 and 50 characters")
    private String address;

    @NotNull(message = "Manufacturing Date cannot be null")
    @PastOrPresent(message = "Manufacturing date must be past or present")
    private LocalDate manufacturingDate;

    @NotNull(message = "Expiry Date cannot be null")
    @Future(message = "Expiry Date must be future")
    private LocalDate expiryDate;

    @NotBlank(message = "Model cannot be null")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Model should be alpha-numeric")
    private String model;
}
