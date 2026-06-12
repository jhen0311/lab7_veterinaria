package com.veterinaria.modelo;

public class Veterinario {

    private int idveterinario;
    private String nombre;
    private String especialidad;

    public Veterinario() {
    }

    public Veterinario(int idveterinario, String nombre, String especialidad) {
        this.idveterinario = idveterinario;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getIdveterinario() {
        return idveterinario;
    }

    public void setIdveterinario(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
