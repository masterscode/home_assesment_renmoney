package com.renmoney_ha.controllers;

import com.auth0.jwt.JWT;
import com.renmoney_ha.models.Role;
import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.RegisterUserRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
import com.renmoney_ha.services.UserService;
import com.renmoney_ha.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1")
public class AuthController {
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        final String jwtToken = jwtUtil.generateToken(userDetails);

        LoginResponse response = new LoginResponse(jwtToken, roles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@Valid @RequestBody RegisterUserRequest request){
        List<Role> roles = new ArrayList<>();

    }
}
