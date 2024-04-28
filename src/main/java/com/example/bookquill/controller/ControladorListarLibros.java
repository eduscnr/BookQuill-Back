package com.example.bookquill.controller;

import com.example.bookquill.model.Libros;
import com.example.bookquill.model.LibrosFavoritos;
import com.example.bookquill.model.LibrosLeidos;
import com.example.bookquill.model.LibrosPendientes;
import com.example.bookquill.model.respuesta.LibrosDTO;
import com.example.bookquill.repository.RepositoryLibrosFavoritos;
import com.example.bookquill.repository.RepositoryLibrosLeidos;
import com.example.bookquill.repository.RepositoryLibrosPendientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/listarLibros")
public class ControladorListarLibros {
    @Autowired
    private RepositoryLibrosFavoritos repositoryLibrosFavoritos;
    @Autowired
    private RepositoryLibrosLeidos repositoryLibrosLeidos;
    @Autowired
    private RepositoryLibrosPendientes repositoryLibrosPendientes;
    private final static int TAMANIO = 10;
    @RequestMapping("/obtenerLibrosFavoritos")
    public LibrosDTO getAllLibrosFavoritos(@RequestParam(defaultValue = "0") int page, @RequestParam int idUsuario){
        Pageable pageable = PageRequest.of(page, TAMANIO);
        List<LibrosFavoritos> listaLibrosFav = repositoryLibrosFavoritos.findLibrosFavoritosByUsuarioIdUsuario(idUsuario, pageable).getContent();
        List<Libros> librosList = listaLibrosFav.stream()
                .map(LibrosFavoritos::getLibros)
                .toList();
        return new LibrosDTO(librosList, librosList.size());
    }
    @RequestMapping("/obtenerLibrosPendientes")
    public LibrosDTO getAllLibrosPendientes(@RequestParam(defaultValue = "0") int page, @RequestParam int idUsuario){
        Pageable pageable = PageRequest.of(page, TAMANIO);
        List<LibrosPendientes> librosPendientesList = repositoryLibrosPendientes.findLibrosPendientesByUsuarioIdUsuario(idUsuario, pageable).getContent();
        List<Libros> librosList = librosPendientesList.stream()
                .map(LibrosPendientes::getLibro)
                .toList();
        return new LibrosDTO(librosList, librosList.size());
    }
    @RequestMapping("/obtenerLibrosLeidos")
    public LibrosDTO getAllLibrosLeidos(@RequestParam(defaultValue = "0") int page, @RequestParam int idUsuario){
        Pageable pageable = PageRequest.of(page, TAMANIO);

        List<LibrosLeidos> librosLeidosList = repositoryLibrosLeidos.findLibrosLeidosByUsuarioIdUsuario(idUsuario, pageable).getContent();
        List<Libros> librosList = librosLeidosList.stream()
                .map(LibrosLeidos::getLibro)
                .toList();
        return new LibrosDTO(librosList, librosList.size());
    }
}
