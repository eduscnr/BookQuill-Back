package com.example.bookquill.controller;

import com.example.bookquill.model.Libros;
import com.example.bookquill.repository.RepositoryLibrosFavoritos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listarLibros")
public class ControladorListarLibros {
    @Autowired
    private RepositoryLibrosFavoritos repositoryLibrosFavoritos;
    @RequestMapping("/favoritos/{idFav}")
    public List<Libros> getAllLibrosFavoritos(@PathVariable int idFav){
        return repositoryLibrosFavoritos.findLibrosFavoritosByUsuarioIdUsuario(idFav);
    }
}
