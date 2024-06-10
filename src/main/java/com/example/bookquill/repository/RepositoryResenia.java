package com.example.bookquill.repository;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.ReseniaPublica;
import com.example.bookquill.model.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RepositoryResenia extends JpaRepository<ReseniaPublica, Integer> {
    Page<ReseniaPublica> findAllByLibros(Libros l, Pageable pageable);
}
