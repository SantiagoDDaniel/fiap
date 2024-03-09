package com.fiap.ecommerce.auth.config;

import com.fiap.ecommerce.auth.model.UserCredential;
import com.fiap.ecommerce.auth.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));

        GrantedAuthority authority = new SimpleGrantedAuthority(userCredential.getRole().toString());

        return new User(userCredential.getName(), userCredential.getPassword(), Collections.singletonList(authority));
    }
}