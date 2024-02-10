package com.fiap.ecommerce.services.impl;

import com.fiap.ecommerce.dtos.UserDto;
import com.fiap.ecommerce.exceptions.BusinessException;
import com.fiap.ecommerce.models.Usuario;
import com.fiap.ecommerce.repositories.UserRepository;
import com.fiap.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        Usuario usuarioJaExiste = userRepository.findByLogin(userDto.login());
        if (usuarioJaExiste != null) {
            throw new BusinessException("Usuário já cadastrado!");
        }
        var passwordHash = passwordEncoder.encode(userDto.password());
        Usuario entity = new Usuario(userDto.name(), userDto.login(), passwordHash, userDto.role());
        Usuario saved = userRepository.save(entity);
        return new UserDto(saved.getName(), saved.getLogin(), saved.getPassword(), saved.getRole());
    }
}
