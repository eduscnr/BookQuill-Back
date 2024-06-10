package com.example.bookquill.security;

import com.example.bookquill.service.UserDatailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationProviderImpl implements AuthenticationProvider {
    @Autowired
    private UserDatailsService service;
    @Autowired
    private SecurityApp securityApp;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = service.loadUserByUsername(username);
        String passwordEncode = user.getPassword();
        System.out.println("PASWROD SIN CODIFICAR: " + password + " y codificada: " + passwordEncode);
        if(!isValidPassword(password, passwordEncode)){
            throw new BadCredentialsException("Password not valid");
        }
        System.out.println(user.getAuthorities());
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    private boolean isValidPassword(String password, String passwordEncripy) {
        return securityApp.passwordEncoder().matches(password, passwordEncripy);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
