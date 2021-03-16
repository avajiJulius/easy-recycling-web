package com.easyrecycling.web.service.implementation.security;

import com.easyrecycling.web.dto.UserDTO;
import com.easyrecycling.web.entity.enums.Role;
import com.easyrecycling.web.exception.RegistrationException;
import com.easyrecycling.web.repository.UserRepository;
import com.easyrecycling.web.sevice.implementation.security.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AuthService {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void givenProperValues_whenRegistration_thenCreateUser() {
        UserDTO user = new UserDTO("user@gmail.com", "password", Role.SINGLE_USER);

        long id = authService.register(user);

        assertTrue(userRepository.existsById(id));
    }

    @Test
    void givenEmptyValues_whenRegistration_thenThrowException() {
        UserDTO user = new UserDTO("", "", null);

        assertThrows(RegistrationException.class, () -> authService.register(user));
    }

    @Test
    void givenEmptyUserDTO_whenRegistration_thenTrowException() {
        UserDTO user = new UserDTO();

        assertThrows(RegistrationException.class, () -> authService.register(user));
    }
}
