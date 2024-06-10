package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.LibrosFavoritos;
import com.example.bookquill.model.LibrosPendientes;
import com.example.bookquill.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryLibrosPendientes extends JpaRepository<LibrosPendientes, Integer> {
    Integer deleteByLibroAndUsuario(Libros l, Usuario u);
    Optional<LibrosPendientes> findLibrosPendientesByLibroAndUsuario(Libros l, Usuario u);
    Page<LibrosPendientes> findLibrosPendientesByUsuarioIdUsuario(Integer usuario_idUsuario, Pageable pageable);
}
