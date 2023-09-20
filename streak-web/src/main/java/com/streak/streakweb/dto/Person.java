package com.streak.streakweb.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Person {
    @NotBlank(message = "First name is missing")
    private final String firstName;
    @NotBlank(message = "Last name is missing")
    private final String lastName;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is missing")
    private final String email;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @NotBlank(message = "Phone number is missing")
    private final String phoneNumber;
}

