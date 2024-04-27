package com.example.bookquill.controller;

import com.example.bookquill.model.Usuario;
import com.example.bookquill.model.respuesta.UsuarioDTO;
import com.example.bookquill.repository.RepositoryUsuario;
import com.example.bookquill.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class ControladorAutenticacion {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;
    private Authentication authentication;
    @PostMapping("/registrar")
    public void registrar(@RequestParam String email, @RequestParam String password){
        System.out.println("registrar");
        repositoryUsuario.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException("Email already exists");
        });
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario 1");
        usuario.setEmail(email);
        usuario.setContrasenia(encoder.encode(password));
        repositoryUsuario.save(usuario);
    }
    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> inicarSesion(@RequestBody Map<String, String> credenciales){
        String username = credenciales.get("username");
        String password = credenciales.get("password");
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String jwt = jwtUtilService.generateToken(userDetails);
            System.out.println("AUTENCIADO: " + jwt);
            return ResponseEntity.ok(jwt);
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
    }

    @PostMapping("/usuario")
    public UsuarioDTO obtenerInformacionUsuario(@RequestParam String nombreUsuario){
        Usuario u = repositoryUsuario.findByEmail(nombreUsuario).orElse(null);
        return  new UsuarioDTO(u.getIdUsuario(), u.getNombre());
    }
}
