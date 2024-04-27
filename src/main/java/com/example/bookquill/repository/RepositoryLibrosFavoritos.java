package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.LibrosFavoritos;
import com.example.bookquill.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryLibrosFavoritos extends JpaRepository<LibrosFavoritos, Integer> {
    @Query("select lf.libros from LibrosFavoritos lf where lf.usuario.idUsuario = :idUsuario")
    List<Libros> findLibrosFavoritosByUsuarioIdUsuario(int idUsuario);
    Integer deleteByLibrosAndUsuario(Libros l, Usuario u);
    Optional<LibrosFavoritos>findLibrosFavoritosByLibrosAndUsuario(Libros l, Usuario u);
}
