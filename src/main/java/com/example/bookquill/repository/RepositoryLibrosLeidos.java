package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.LibrosLeidos;
import com.example.bookquill.model.LibrosPendientes;
import com.example.bookquill.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryLibrosLeidos extends JpaRepository<LibrosLeidos, Integer> {
    Integer deleteByLibroAndUsuario(Libros l, Usuario u);
    Optional<LibrosLeidos> findLibrosLeidosByLibroAndUsuario(Libros l, Usuario u);
}
