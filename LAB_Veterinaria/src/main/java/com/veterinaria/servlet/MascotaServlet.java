package com.veterinaria.servlet;

import com.veterinaria.dao.MascotaDao;
import com.veterinaria.modelo.Especie;
import com.veterinaria.modelo.Mascota;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet encargado de listar las mascotas registradas.
 * Soporta filtro por especie mediante el parámetro "especieId"
 * (Pregunta 2 y Pregunta 4).
 */
@WebServlet("/mascotas")
public class MascotaServlet extends HttpServlet {

    private final MascotaDao mascotaDao = new MascotaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetro de filtro proveniente del ComboBox de especies
        String especieIdParam = request.getParameter("especieId");
        int especieId = 0;

        if (especieIdParam != null && !especieIdParam.isEmpty()) {
            try {
                especieId = Integer.parseInt(especieIdParam);
            } catch (NumberFormatException e) {
                especieId = 0;
            }
        }

        List<Mascota> listaMascotas = mascotaDao.listar(especieId);
        List<Especie> listaEspecies = mascotaDao.listarEspecies();

        request.setAttribute("listaMascotas", listaMascotas);
        request.setAttribute("listaEspecies", listaEspecies);
        request.setAttribute("especieSeleccionada", especieId);

        request.getRequestDispatcher("/listado.jsp").forward(request, response);
    }
}
