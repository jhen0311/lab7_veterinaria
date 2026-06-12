package com.veterinaria.modelo;

public class Especie {

    private int idespecie;
    private String nombre;

    public Especie() {
    }

    public Especie(int idespecie, String nombre) {
        this.idespecie = idespecie;
        this.nombre = nombre;
    }

    public int getIdespecie() {
        return idespecie;
    }

    public void setIdespecie(int idespecie) {
        this.idespecie = idespecie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
