package com.example.bookquill.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity(name = "resenia_privadas")
public class ReseniaPrivada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena_privada")
    private Integer idReseniaPrivada;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libros libros;
    private String texto;
    @Column(name = "fecha")
    private Date fechaPublicacion;

    public ReseniaPrivada() {
    }

    public ReseniaPrivada(Integer idReseniaPrivada, Usuario usuario, Libros libros, String texto, Date fechaPublicacion) {
        this.idReseniaPrivada = idReseniaPrivada;
        this.usuario = usuario;
        this.libros = libros;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
    }

    public ReseniaPrivada(Usuario usuario, Libros libros, String texto, Date fechaPublicacion) {
        this.usuario = usuario;
        this.libros = libros;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getIdReseniaPrivada() {
        return idReseniaPrivada;
    }

    public void setIdReseniaPrivada(Integer idReseniaPrivada) {
        this.idReseniaPrivada = idReseniaPrivada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libros getLibro() {
        return libros;
    }

    public void setLibro(Libros libros) {
        this.libros = libros;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicación(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "ReseniaPrivada{" +
                "idReseniaPrivada=" + idReseniaPrivada +
                ", usuario=" + usuario +
                ", libro=" + libros +
                ", texto='" + texto + '\'' +
                ", fechaPublicación=" + fechaPublicacion +
                '}';
    }
}
