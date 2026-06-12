package com.veterinaria.dao;

import com.veterinaria.modelo.Dueno;
import com.veterinaria.modelo.Especie;
import com.veterinaria.modelo.Mascota;
import com.veterinaria.modelo.Veterinario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDao extends DaoBase {

    /**
     * Lista todas las mascotas con la información de especie,
     * veterinario y dueño (mediante INNER JOIN).
     */
    public List<Mascota> listar() {
        return listar(0);
    }

    /**
     * Lista las mascotas filtrando por especie.
     * Si especieId es 0, se devuelven todas las mascotas.
     */
    public List<Mascota> listar(int especieId) {
        List<Mascota> lista = new ArrayList<>();

        String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, "
                + "e.idespecie, e.nombre AS especie_nombre, "
                + "v.idveterinario, v.nombre AS veterinario_nombre, "
                + "d.iddueno, d.nombre AS dueno_nombre "
                + "FROM mascota m "
                + "INNER JOIN especie e ON m.especie_id = e.idespecie "
                + "INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario "
                + "INNER JOIN dueno d ON m.dueno_id = d.iddueno ";

        if (especieId > 0) {
            sql += "WHERE m.especie_id = ? ";
        }
        sql += "ORDER BY m.idmascota";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (especieId > 0) {
                ps.setInt(1, especieId);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mascota m = new Mascota();
                    m.setIdmascota(rs.getInt("idmascota"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEdad(rs.getInt("edad"));
                    m.setPeso(rs.getBigDecimal("peso"));

                    Especie especie = new Especie();
                    especie.setIdespecie(rs.getInt("idespecie"));
                    especie.setNombre(rs.getString("especie_nombre"));
                    m.setEspecie(especie);

                    Veterinario veterinario = new Veterinario();
                    veterinario.setIdveterinario(rs.getInt("idveterinario"));
                    veterinario.setNombre(rs.getString("veterinario_nombre"));
                    m.setVeterinario(veterinario);

                    Dueno dueno = new Dueno();
                    dueno.setIddueno(rs.getInt("iddueno"));
                    dueno.setNombre(rs.getString("dueno_nombre"));
                    m.setDueno(dueno);

                    lista.add(m);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Crea una nueva mascota en la base de datos.
     * Implementación del método abstracto crear() heredado de DaoBase.
     */
    @Override
    public boolean crear(Object entidad) {
        if (!(entidad instanceof Mascota)) {
            return false;
        }
        Mascota mascota = (Mascota) entidad;

        String sql = "INSERT INTO mascota (nombre, edad, peso, especie_id, veterinario_id, dueno_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setInt(2, mascota.getEdad());
            ps.setBigDecimal(3, mascota.getPeso());
            ps.setInt(4, mascota.getEspecieId());
            ps.setInt(5, mascota.getVeterinarioId());
            ps.setInt(6, mascota.getDuenoId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una mascota según su id.
     * Implementación del método abstracto borrar() heredado de DaoBase.
     */
    @Override
    public boolean borrar(int id) {
        String sql = "DELETE FROM mascota WHERE idmascota = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // Métodos auxiliares para llenar los ComboBox (Pregunta 3 y 4)
    // =========================================================

    public List<Especie> listarEspecies() {
        List<Especie> lista = new ArrayList<>();
        String sql = "SELECT idespecie, nombre FROM especie ORDER BY nombre";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Especie(rs.getInt("idespecie"), rs.getString("nombre")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Veterinario> listarVeterinarios() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT idveterinario, nombre, especialidad FROM veterinario ORDER BY nombre";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Veterinario(
                        rs.getInt("idveterinario"),
                        rs.getString("nombre"),
                        rs.getString("especialidad")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Dueno> listarDuenos() {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT iddueno, nombre, telefono FROM dueno ORDER BY nombre";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Dueno(
                        rs.getInt("iddueno"),
                        rs.getString("nombre"),
                        rs.getString("telefono")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
