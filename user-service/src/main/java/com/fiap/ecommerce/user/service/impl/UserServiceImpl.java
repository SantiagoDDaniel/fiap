package com.fiap.ecommerce.user.service.impl;

import com.fiap.ecommerce.user.dto.UserDto;
import com.fiap.ecommerce.user.exceptions.BusinessException;
import com.fiap.ecommerce.user.model.Usuario;
import com.fiap.ecommerce.user.repository.UserRepository;
import com.fiap.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    public UserDto save(UserDto userDto) {
        Usuario usuarioJaExiste = this.userRepository.findByLogin(userDto.login());
        if (usuarioJaExiste != null) {
            throw new BusinessException("Usuário já cadastrado!");
        } else {
            String passwordHash = this.passwordEncoder.encode(userDto.password());
            Usuario entity = new Usuario(userDto.name(), userDto.login(), passwordHash, userDto.role());
            Usuario saved = this.userRepository.save(entity);
            return new UserDto(saved.getName(), saved.getLogin(), saved.getPassword(), saved.getRole());
        }
    }
}