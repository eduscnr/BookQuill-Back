package com.example.bookquill.service;

import com.example.bookquill.model.Usuario;
import com.example.bookquill.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDatailsService implements UserDetailsService {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<Usuario> useUsuarioOptional = repositoryUsuario.findByEmail(username);
        if (useUsuarioOptional.isPresent()){
            return useUsuarioOptional.get();
        }else {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
    }
}
