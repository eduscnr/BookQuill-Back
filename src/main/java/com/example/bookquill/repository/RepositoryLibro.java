package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.respuesta.LibrosPopularesResultados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryLibro extends JpaRepository<Libros, Integer> {
    @Query("SELECT l from libros l order by l.estrellas desc ,l.idLibro asc fetch first 10 row only")
    List<Libros> findTop10ByLibros();
    Page<Libros> findByGenero_Tipo(String tipo, Pageable pageable);
    @Query(value = "SELECT * from libros l order by Rand() limit 10", nativeQuery = true)
    List<Libros> findRandomLibros();
    @Query(value = "SELECT * FROM libros l order by l.estrellas desc", nativeQuery = true)
    Page<Libros> findLibrosPopulares(Pageable pageable);
}
