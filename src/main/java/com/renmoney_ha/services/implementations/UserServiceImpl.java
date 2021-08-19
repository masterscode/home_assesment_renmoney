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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private final ModelMapper modelmapper;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByEmail(s).get();
//        return repository.findByEmail(s).orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        final ModelMapper modelmapper = new ModelMapper();
        User userToRegister = modelmapper.map(request, User.class);
        Role role = roleRepository.findByName(ERole.USER);
        log.info(role.toString());
        userToRegister.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userToRegister.setRoles(List.of(role));

        User registeredUser = repository.save(userToRegister);
        return modelmapper.map(registeredUser, UserRegistrationResponse.class);

    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {

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
