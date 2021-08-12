package com.renmoney_ha.services.implementations;

import com.renmoney_ha.configurations.security.ERole;
import com.renmoney_ha.models.Role;
import com.renmoney_ha.models.User;
import com.renmoney_ha.payloads.requests.RegisterUserRequest;
import com.renmoney_ha.repositories.RoleRepository;
import com.renmoney_ha.repositories.UserRepository;
import com.renmoney_ha.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;



    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByEmail(s).orElseGet(User::new);
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterUserRequest request) {
        final ModelMapper mapper = new ModelMapper();

        User userToRegister = mapper.map(request,User.class);
        Role role = roleRepository.findByName(ERole.USER);
        userToRegister.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userToRegister.setRoles(List.of(role));

        return ResponseEntity.ok().body(
                repository.save(userToRegister)
        );

    }
}
