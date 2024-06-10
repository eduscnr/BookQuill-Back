package com.example.bookquill.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
@Entity(name = "resenia_publicas")
public class ReseniaPublica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena_publica")
    private Integer idReseniaPublica;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libros libros;
    private String texto;
    @Column(name = "fecha")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
    private Date fechaPublicada;

    public ReseniaPublica() {
    }

    public ReseniaPublica(Integer idReseniaPublica, Usuario usuario, Libros libros, String texto, Date fechaPublicada) {
        this.idReseniaPublica = idReseniaPublica;
        this.usuario = usuario;
        this.libros = libros;
        this.texto = texto;
        this.fechaPublicada = fechaPublicada;
    }

    public ReseniaPublica(Usuario usuario, Libros libros, String texto, Date fechaPublicada) {
        this.usuario = usuario;
        this.libros = libros;
        this.texto = texto;
        this.fechaPublicada = fechaPublicada;
    }

    public Integer getIdReseniaPublica() {
        return idReseniaPublica;
    }

    public void setIdReseniaPublica(Integer idReseniaPublica) {
        this.idReseniaPublica = idReseniaPublica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaPublicada() {
        return fechaPublicada;
    }

    public void setFechaPublicada(Date fechaPublicada) {
        this.fechaPublicada = fechaPublicada;
    }

    @Override
    public String toString() {
        return "ReseniaPublica{" +
                "idReseniaPublica=" + idReseniaPublica +
                ", usuario=" + usuario +
                ", libros=" + libros +
                ", texto='" + texto + '\'' +
                ", fechaPublicada=" + fechaPublicada +
                '}';
    }
}
