package com.example.bookquill.controller;

import com.example.bookquill.model.*;
import com.example.bookquill.model.respuesta.DetalleResenia;
import com.example.bookquill.model.respuesta.LibrosGenerosRespuesta;
import com.example.bookquill.model.respuesta.LibrosPopularesResultados;
import com.example.bookquill.model.respuesta.UsuarioRespuesta;
import com.example.bookquill.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")
public class ControllerLibros {
    private final RepositoryLibro repositoryLibro;
    private final RepositoryGenero repositoryGenero;
    @Autowired
    private RepositoryLibrosFavoritos repositoryLibrosFavoritos;
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private RepositoryResenia repositoryResenia;
    @Autowired
    private RepositoryLibrosPendientes repositoryLibrosPendientes;
    @Autowired
    private RepositoryLibrosLeidos repositoryLibrosLeidos;
    private final static int TAMANIO = 20;
    private final static int MAXIMO_PAGINA = 10;

    public ControllerLibros(RepositoryLibro repositoryLibro, RepositoryGenero repositoryGenero) {
        this.repositoryLibro = repositoryLibro;
        this.repositoryGenero = repositoryGenero;
    }

    @RequestMapping("/listar")
    public List<Libros> getLibros(@RequestParam(defaultValue = "0") int page) {
        final Pageable pageable = PageRequest.of(page, TAMANIO);
        return repositoryLibro.findAll(pageable).getContent();
    }

    @RequestMapping("/top10")
    public List<Libros> top10() {
        return repositoryLibro.findTop10ByLibros();
    }

    @RequestMapping("/tipoGenero")
    public LibrosGenerosRespuesta getLibroByTipo(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "Romance") String tipo) {
        final Pageable pageable = PageRequest.of(page, TAMANIO);
        List<Libros> libros = repositoryLibro.findByGenero_Tipo(tipo, pageable).getContent();
        int total = (int) repositoryLibro.findByGenero_Tipo(tipo, pageable).getTotalElements();
        return new LibrosGenerosRespuesta(libros, total);
    }

    @RequestMapping("/random")
    public List<Libros> getLibrosRamdon() {
        return repositoryLibro.findRandomLibros();
    }

    @RequestMapping("/generos")
    public List<Genero> getAllGeneros() {
        return repositoryGenero.findAll();
    }

    @RequestMapping("/{id}")
    public Libros getLibroId(@PathVariable int id) {
        return repositoryLibro.findById(id).orElse(null);
    }

    @RequestMapping("/populares")
    public LibrosPopularesResultados librosPopulares(@RequestParam(defaultValue = "0") int page) {
        if (page > MAXIMO_PAGINA) {
            throw new IllegalArgumentException("Página no válida. El límite máximo es " + MAXIMO_PAGINA);
        }
        Pageable pageable = PageRequest.of(page, TAMANIO);
        return new LibrosPopularesResultados(repositoryLibro.findLibrosPopulares(pageable).getContent(), MAXIMO_PAGINA * TAMANIO);
    }

    @RequestMapping("/favorito/{idFav}")
    public List<Libros> getAllLibrosFavoritos(@PathVariable int idFav) {
        return repositoryLibrosFavoritos.findLibrosFavoritosByUsuarioIdUsuario(idFav);
    }

    @RequestMapping("/usuario")
    public UsuarioRespuesta getUsuario(@RequestParam String email) {
        Usuario usuario = repositoryUsuario.findByEmail(email).get();
        return new UsuarioRespuesta(usuario.getNombre(), usuario.getEmail(), usuario.getIdUsuario());
    }

    @RequestMapping("/libroFavorito")
    public void insertado(@RequestParam int idLibro, @RequestParam int idUsuario) {
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosFavoritos.save(new LibrosFavoritos(l, u));
    }

    @RequestMapping("/eliminarFav")
    @Transactional
    public void eliminarFavorito(@RequestParam int idLibro, @RequestParam int idUsuario) {
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosFavoritos.deleteByLibrosAndUsuario(l, u);
    }

    @RequestMapping("/esFavoritos")
    @Transactional
    public boolean favorito(@RequestParam int idLibro, @RequestParam int idUsuario) {
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        Libros l = repositoryLibro.getReferenceById(idLibro);
        LibrosFavoritos librosFavoritos = repositoryLibrosFavoritos.findLibrosFavoritosByLibrosAndUsuario(l, u).orElse(null);
        if (librosFavoritos != null) {
            return true;
        } else {
            return false;
        }
    }
    @PostMapping("/insertarLibroPendiente")
    public void insertarLibroPendiente(@RequestParam int idLibro, @RequestParam int idUsuario){
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosPendientes.save(new LibrosPendientes(l,u));
    }
    @PostMapping("/insertarLibroLeido")
    public void insertarLibrosLeidos(@RequestParam int idLibro, @RequestParam int idUsuario){
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosLeidos.save(new LibrosLeidos(l,u));
    }
    @PostMapping("/eliminarPendiente")
    @Transactional
    public void eliminarLibrosPendiente(@RequestParam int idLibro, @RequestParam int idUsuario){
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosPendientes.deleteByLibroAndUsuario(l, u);
    }
    @PostMapping("/eliminarLeido")
    @Transactional
    public void eliminarLibroLeido(@RequestParam int idLibro, @RequestParam int idUsuario){
        Libros l = repositoryLibro.getReferenceById(idLibro);
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        repositoryLibrosLeidos.deleteByLibroAndUsuario(l, u);
    }
    @RequestMapping("/esPendiente")
    @Transactional
    public boolean esPendiente(@RequestParam int idLibro, @RequestParam int idUsuario) {
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        Libros l = repositoryLibro.getReferenceById(idLibro);
        LibrosPendientes librosPendientes = repositoryLibrosPendientes.findLibrosPendientesByLibroAndUsuario(l, u).orElse(null);
        if (librosPendientes != null) {
            return true;
        } else {
            return false;
        }
    }
    @RequestMapping("/esLeido")
    @Transactional
    public boolean esLeido(@RequestParam int idLibro, @RequestParam int idUsuario) {
        Usuario u = repositoryUsuario.getReferenceById(idUsuario);
        Libros l = repositoryLibro.getReferenceById(idLibro);
        LibrosLeidos librosLeidos = repositoryLibrosLeidos.findLibrosLeidosByLibroAndUsuario(l, u).orElse(null);
        if (librosLeidos != null) {
            return true;
        } else {
            return false;
        }
    }
    @GetMapping("/mostrarResenia")
    public List<DetalleResenia> mostrarResenia(@RequestParam(defaultValue = "0") int page, @RequestParam int idLibro) {
        Pageable pageable = PageRequest.of(page, TAMANIO);
        Libros l = repositoryLibro.getReferenceById(idLibro);
        List<ReseniaPublica> resenias = repositoryResenia.findByLibros(l, pageable).getContent();
        List<DetalleResenia> listaResenia = resenias.stream()
                .map(this::parseReseniaToDetalleResenia)
                .collect(Collectors.toList());
        return listaResenia;
    }

    private DetalleResenia parseReseniaToDetalleResenia(ReseniaPublica r) {
        DetalleResenia detalleResenia = new DetalleResenia();
        detalleResenia.setDescripcionResenia(r.getTexto());
        detalleResenia.setNombreUsuario(r.getUsuario().getNombre());
        detalleResenia.setFechaResenia(r.getFechaPublicada());
        return detalleResenia;
    }
}
