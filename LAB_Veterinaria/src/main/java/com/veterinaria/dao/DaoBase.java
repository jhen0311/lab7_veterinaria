package com.veterinaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase abstracta que centraliza la configuración de la conexión JDBC.
 * Todas las clases DAO del sistema deben extender de esta clase para
 * reutilizar la conexión a la base de datos y respetar el contrato de
 * creación y borrado de entidades.
 */
public abstract class DaoBase {

    // ===== Configuración de conexión JDBC =====
    private static final String URL = "jdbc:mysql://localhost:3306/Veterinaria?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "root"; // Cambiar según configuración local

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MySQL", e);
        }
    }

    /**
     * Obtiene una nueva conexión a la base de datos.
     * Las clases hijas la usan para ejecutar sus consultas.
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /**
     * Crea una nueva entidad en la base de datos.
     * Cada DAO hijo define qué tabla y campos utiliza.
     *
     * @param entidad objeto a registrar (ej. Mascota)
     * @return true si la operación fue exitosa
     */
    public abstract boolean crear(Object entidad);

    /**
     * Elimina una entidad de la base de datos según su id.
     *
     * @param id identificador de la entidad a eliminar
     * @return true si la operación fue exitosa
     */
    public abstract boolean borrar(int id);
}
