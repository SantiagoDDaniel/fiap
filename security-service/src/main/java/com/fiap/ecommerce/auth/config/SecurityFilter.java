package com.fiap.ecommerce.auth.config;

import com.fiap.ecommerce.auth.model.Usuario;
import com.fiap.ecommerce.auth.repository.UserRepository;
import com.fiap.ecommerce.auth.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AutenticacaoService service;
    @Autowired
    private UserRepository userRepository;

    public SecurityFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.extraiTokeHeader(request);
        if (token != null) {
            String login = this.service.validateTokenJwt(token);
            if (login != null) {
                Usuario usuario = this.userRepository.findByLogin(login);
                if (usuario != null) {
                    User userDetails = new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    public String extraiTokeHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        } else {
            return !authHeader.split(" ")[0].equals("Bearer") ? null : authHeader.split(" ")[1];
        }
    }
}
