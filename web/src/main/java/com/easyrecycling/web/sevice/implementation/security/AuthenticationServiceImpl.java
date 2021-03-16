package com.easyrecycling.web.sevice.implementation.security;

import com.easyrecycling.web.dto.UserDTO;
import com.easyrecycling.web.entity.model.User;
import com.easyrecycling.web.exception.JwtAuthenticationException;
import com.easyrecycling.web.exception.RegistrationException;
import com.easyrecycling.web.repository.UserRepository;
import com.easyrecycling.web.sevice.api.security.AuthenticationService;
import com.easyrecycling.web.sevice.implementation.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                                     JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public long register(UserDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().equals("")
                || dto.getPassword() == null || dto.getPassword().equals("")
                || dto.getRole() == null) {
            throw new RegistrationException("Field is Null");
        }
        User user = new User.Builder()
                .withEmail(dto.getEmail())
                .withPassword(passwordEncoder.encode(dto.getPassword()))
                .withEnabled(true)
                .withRole(dto.getRole())
                .build();

        userRepository.save(user);

        return user.getId();
    }

    @Override
    public Map<Object, Object> authenticate(UserDTO request) {
        try {
            String email = request.getEmail();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email, request.getPassword()));

            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException("User don't exist"));

            String token = jwtTokenProvider.createToken(email, user.getRole());


            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return response;

        } catch (AuthenticationException ex) {
            throw new JwtAuthenticationException("Invalid email or/and password", HttpStatus.FORBIDDEN);
        }
    }
}
