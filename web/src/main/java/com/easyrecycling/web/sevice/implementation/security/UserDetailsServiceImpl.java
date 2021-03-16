package com.easyrecycling.web.sevice.implementation.security;

import com.easyrecycling.web.entity.enums.Role;
import com.easyrecycling.web.entity.model.User;
import com.easyrecycling.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User don't exist"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                user.isEnable(), user.isEnable(),
                user.isEnable(), user.isEnable(),
                getAuthorities(user.getRole())
        );
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Role role) {
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
