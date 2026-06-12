package com.veterinaria.servlet;

import com.veterinaria.dao.MascotaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
