package com.streak.streakweb.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class Person {
    @NotBlank(message = "First name is missing")
    String firstName;
    @NotBlank(message = "Last name is missing")
    String lastName;
    @Email(message = "Invalid email address")
    String email;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    String phoneNumber;
    List<Integer> numbers;
}
