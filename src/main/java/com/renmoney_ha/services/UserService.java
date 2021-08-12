package com.renmoney_ha.services;

import com.renmoney_ha.payloads.requests.RegisterUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> registerUser(RegisterUserRequest request);
}
