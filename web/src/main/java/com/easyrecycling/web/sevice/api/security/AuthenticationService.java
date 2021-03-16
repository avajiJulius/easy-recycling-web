package com.easyrecycling.web.sevice.api.security;

import com.easyrecycling.web.dto.UserDTO;

import java.util.Map;

public interface AuthenticationService {

    long register(UserDTO user);

    Map<Object, Object> authenticate(UserDTO request);
}
