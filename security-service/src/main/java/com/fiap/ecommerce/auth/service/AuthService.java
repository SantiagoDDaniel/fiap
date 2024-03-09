package com.fiap.ecommerce.auth.service;

import com.fiap.ecommerce.auth.model.UserCredential;
import com.fiap.ecommerce.auth.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        repository.findByName(credential.getName()).ifPresent(userCredential -> {
            throw new RuntimeException("user already exists");
        });
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There are no roles present for the user.")).getAuthority();
        return jwtService.generateToken(username, role);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
    public void deleteUserbyId(int userId) {
        repository.findById(userId).ifPresent(repository::delete);
    }


}
