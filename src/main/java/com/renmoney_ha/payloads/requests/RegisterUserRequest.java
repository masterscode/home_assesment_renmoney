package com.renmoney_ha.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "first name cannot be empty")
    private String firstName;

    @NotNull(message = "last name cannot be empty")
    private String lastName;

    @NotNull(message = "Email Field should not be empty")
    @Email(message = "Invalid Email")
    private String email;


    @NotNull(message = "Password cannot be empty")
    @Size(min = 6, max = 24)
    private String password;

}
