package com.renmoney_ha.services.implementations;

import com.renmoney_ha.configurations.security.ERole;
import com.renmoney_ha.models.Role;
import com.renmoney_ha.models.User;
import com.renmoney_ha.payloads.requests.LoginRequest;
import com.renmoney_ha.payloads.requests.UserRegistrationRequest;
import com.renmoney_ha.payloads.response.LoginResponse;
import com.renmoney_ha.payloads.response.UserRegistrationResponse;
import com.renmoney_ha.repositories.RoleRepository;
import com.renmoney_ha.repositories.UserRepository;
import com.renmoney_ha.services.UserService;
import com.renmoney_ha.utils.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelmapper = new ModelMapper();
    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByEmail(s).orElseThrow();
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        var validationResult = validator.validate(request);
        if (!validationResult.isEmpty()) throw new ValidationException();
        User userToRegister = modelmapper.map(request, User.class);
        Role role = roleRepository.findByName(ERole.USER);
        userToRegister.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userToRegister.setRoles(List.of(role));

        User registeredUser = repository.save(userToRegister);
        return modelmapper.map(registeredUser, UserRegistrationResponse.class);

    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {

        var validationResult = validator.validate(request);
        if (!validationResult.isEmpty()) throw new ValidationException();

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails loggedInUser = loadUserByUsername(request.getEmail());

        final String jwtToken = jwtUtil.generateToken(loggedInUser);

        final List<String> roles = loggedInUser
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new LoginResponse(jwtToken, roles);
    }
}
