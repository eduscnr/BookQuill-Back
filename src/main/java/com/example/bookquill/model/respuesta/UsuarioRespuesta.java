package com.example.bookquill.model.respuesta;

public class UsuarioRespuesta {
    private String nombre;
    private String email;
    private int id;

    public UsuarioRespuesta() {
    }

    public UsuarioRespuesta(String nombre, String email, int id) {
        this.nombre = nombre;
        this.email = email;
        this.id = id;
    }

    public UsuarioRespuesta(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
