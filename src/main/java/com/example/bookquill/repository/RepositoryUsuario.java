package com.example.bookquill.repository;

import com.example.bookquill.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUsuario extends JpaRepository<Usuario, Integer> {
    @EntityGraph(attributePaths = {"authorities"})
    Optional<Usuario> findByEmail(String email);
}
