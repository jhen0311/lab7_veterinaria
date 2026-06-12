package com.veterinaria.servlet;

import com.veterinaria.dao.MascotaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de eliminar una mascota según su id.
 * Utiliza el método borrar() heredado de DaoBase a través de MascotaDao.
 * Pregunta 3.
 */
@WebServlet("/borrarMascota")
public class BorrarMascotaServlet extends HttpServlet {

    private final MascotaDao mascotaDao = new MascotaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                mascotaDao.borrar(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("mascotas");
    }
}
