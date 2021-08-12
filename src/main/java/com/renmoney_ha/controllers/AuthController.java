package com.renmoney_ha.controllers;

import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.RegisterUserRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private Validator validator;


    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails loggedInUser = userService.loadUserByUsername(request.getEmail());
        final String jwtToken = jwtUtil.generateToken(loggedInUser);

        LoginResponse response = new LoginResponse(jwtToken, List.of(" "));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@Valid @RequestBody RegisterUserRequest request, BindingResult validationResult) {
        var result = validationResult.getAllErrors();
        if (!result.isEmpty()) return ResponseEntity.badRequest().body(result);
        return userService.registerUser(request);
    }
}
