package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.LibrosFavoritos;
import com.example.bookquill.model.Usuario;
import com.example.bookquill.model.respuesta.LibrosDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryLibrosFavoritos extends JpaRepository<LibrosFavoritos, Integer> {
    Integer deleteByLibrosAndUsuario(Libros l, Usuario u);
    Optional<LibrosFavoritos>findLibrosFavoritosByLibrosAndUsuario(Libros l, Usuario u);
    Page<LibrosFavoritos> findLibrosFavoritosByUsuarioIdUsuario(Integer usuario_idUsuario, Pageable pageable);
}
