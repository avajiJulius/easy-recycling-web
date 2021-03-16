package com.easyrecycling.web.sevice.implementation.security;

import com.easyrecycling.web.dto.UserDTO;
import com.easyrecycling.web.entity.model.User;
import com.easyrecycling.web.exception.RegistrationException;
import com.easyrecycling.web.repository.UserRepository;
import com.easyrecycling.web.sevice.api.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                .withPassword(dto.getPassword())
                .withEnabled(true)
                .withRole(dto.getRole())
                .build();

        userRepository.save(user);

        return user.getId();
    }
}
