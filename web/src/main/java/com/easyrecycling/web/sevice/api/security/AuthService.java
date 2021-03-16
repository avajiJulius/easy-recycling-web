package com.easyrecycling.web.sevice.api.security;

import com.easyrecycling.web.dto.UserDTO;

public interface AuthService {

    long register(UserDTO user);
}
