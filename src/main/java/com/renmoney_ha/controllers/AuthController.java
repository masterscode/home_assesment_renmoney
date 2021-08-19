package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.UserRegistrationRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
import com.renmoney_ha.payloads.response.UserRegistrationResponse;
import com.renmoney_ha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>doLogin(@Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                userService.loginUser(request)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> doRegister(@Valid @RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(
                userService.registerUser(request)
        );
    }
}
