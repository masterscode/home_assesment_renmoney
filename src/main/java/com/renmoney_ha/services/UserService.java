package com.renmoney_ha.services;

import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.UserRegistrationRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
import com.renmoney_ha.payloads.response.UserRegistrationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegistrationResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
}
