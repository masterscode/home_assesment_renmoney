package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.UserRegistrationRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
import com.renmoney_ha.payloads.response.UserRegistrationResponse;
import com.renmoney_ha.services.UserService;
import com.renmoney_ha.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest request) {

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
