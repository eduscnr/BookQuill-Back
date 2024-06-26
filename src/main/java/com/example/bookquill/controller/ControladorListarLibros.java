package com.example.bookquill.controller;

import com.example.bookquill.model.*;
import com.example.bookquill.model.respuesta.LibrosDTO;
import com.example.bookquill.model.respuesta.ReseniaDTO;
import com.example.bookquill.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private RepositoryLibro repositoryLibro;
    @Autowired
    private RepositoryResenia repositoryResenia;
    @Autowired
    private RepositoryUsuario repositoryUsuario;
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
    @RequestMapping("/filtrarBusqueda")
    public LibrosDTO mostrarFiltradoBusqueda(@RequestParam(defaultValue = "0") int page, @RequestParam String filtro){
        Pageable pageable = PageRequest.of(page, TAMANIO);
        String filtroComodines = "%" + filtro + "%";
        List<Libros> librosList = repositoryLibro.buscadorFiltrado(filtroComodines, pageable).getContent();
        return new LibrosDTO(librosList, librosList.size());
    }

    @RequestMapping("/agregarResenia")
    public ResponseEntity<Void> agregarResenia(@RequestBody ReseniaDTO reseniaDTO){
        try {
            Usuario usuario = repositoryUsuario.getReferenceById(reseniaDTO.getIdUsuario());
            Libros libros = repositoryLibro.getReferenceById(reseniaDTO.getIdLibro());
            ReseniaPublica reseniaPublica = new ReseniaPublica(usuario, libros, reseniaDTO.getTexto(), new Date());
            repositoryResenia.save(reseniaPublica);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
