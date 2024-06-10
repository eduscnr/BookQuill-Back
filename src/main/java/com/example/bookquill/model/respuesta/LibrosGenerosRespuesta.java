package com.example.bookquill.model.respuesta;

import com.example.bookquill.model.Libros;

import java.util.List;

public class LibrosGenerosRespuesta {
    private List<Libros> librosGeneros;
    private int total;

    public LibrosGenerosRespuesta(List<Libros> librosGeneros, int total) {
        this.librosGeneros = librosGeneros;
        this.total = total;
    }

    public List<Libros> getLibrosGeneros() {
        return librosGeneros;
    }

    public void setLibrosGeneros(List<Libros> librosGeneros) {
        this.librosGeneros = librosGeneros;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
