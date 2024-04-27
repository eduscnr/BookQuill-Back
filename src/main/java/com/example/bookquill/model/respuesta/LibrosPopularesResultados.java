package com.example.bookquill.model.respuesta;

import com.example.bookquill.model.Libros;

import java.util.List;

public class LibrosPopularesResultados {
    private List<Libros> listLibrosPopulares;
    private int total;

    public LibrosPopularesResultados(List<Libros> listLibrosPopulares, int total) {
        this.listLibrosPopulares = listLibrosPopulares;
        this.total = total;
    }

    public List<Libros> getListLibrosPopulares() {
        return listLibrosPopulares;
    }

    public void setListLibrosPopulares(List<Libros> listLibrosPopulares) {
        this.listLibrosPopulares = listLibrosPopulares;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
