package com.example.gestudent.POJO;

import java.io.Serializable;

public class TareaApunte implements Serializable {


    private String id;

    private String titulo;
    private String descripcion;


    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public TareaApunte () {
    }


    public TareaApunte ( String id , String titulo , String descripcion ) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public TareaApunte ( String titulo , String descripcion ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo ( String titulo ) {
        this.titulo = titulo;
    }

    public String getDescripcion () {
        return descripcion;
    }

    public void setDescripcion ( String descripcion ) {
        this.descripcion = descripcion;
    }
}
