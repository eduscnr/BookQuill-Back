package com.example.bookquill.model;

import jakarta.persistence.*;

@Entity(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer idGenero;
    private String tipo;

    public Genero() {
    }

    public Genero(Integer idGenero, String tipo) {
        this.idGenero = idGenero;
        this.tipo = tipo;
    }

    public Genero(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
