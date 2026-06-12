package com.veterinaria.modelo;

import java.math.BigDecimal;

public class Mascota {

    private int idmascota;
    private String nombre;
    private int edad;
    private BigDecimal peso;

    // Objetos completos para mostrar nombre en la vista
    private Especie especie;
    private Veterinario veterinario;
    private Dueno dueno;

    // IDs usados al crear/guardar (vienen del ComboBox)
    private int especieId;
    private int veterinarioId;
    private int duenoId;

    public Mascota() {
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public int getEspecieId() {
        return especieId;
    }

    public void setEspecieId(int especieId) {
        this.especieId = especieId;
    }

    public int getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }
}
