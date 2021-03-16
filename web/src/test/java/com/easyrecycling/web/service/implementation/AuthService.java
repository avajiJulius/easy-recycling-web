package com.easyrecycling.web.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthService {

    @Test
    void givenProperValues_whenSignUp_thenCreateUser() {

    }

    @Test
    void givenEmptyValues_whenSignUp_thenThrowException() {

    }

    @Test
    void givenEmptyValues_whenSignIn_thenThrowException() {

    }

    @Test
    void givenRightEmailAndPassword_whenSignIn_thenAuthorize() {

    }

    @Test
    void givenRightEmailAndWrongPassword_whenSignIn_thenThrowException() {

    }

    @Test
    void givenNotExistingEmail_whenSignIn_thenThrowException() {

    }
}
