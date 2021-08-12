package com.renmoney_ha.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password field cannot be empty")
    private String password;
}
