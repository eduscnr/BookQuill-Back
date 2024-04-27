package com.example.bookquill.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String nombre;
    @Column(name = "password")
    private String contrasenia;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<UserAuthority> authorities = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<LibrosFavoritos> librosFavoritos = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<LibrosLeidos> librosLeidos = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<LibrosPendientes> librosPendientes = new ArrayList<>();

    public Usuario() {
        authorities.add(UserAuthority.ROLE_USERAPP);
    }

    public Usuario(Integer idUsuario, String nombre, String contrasenia, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public Usuario(String nombre, String contrasenia, String email) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                .toList();
    }

    public List<LibrosFavoritos> getLibrosFavoritos() {
        return librosFavoritos;
    }

    public List<LibrosLeidos> getLibrosLeidos() {
        return librosLeidos;
    }

    public List<LibrosPendientes> getLibrosPendientes() {
        return librosPendientes;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
