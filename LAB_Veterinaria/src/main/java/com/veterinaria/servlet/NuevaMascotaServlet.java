package com.veterinaria.servlet;

import com.veterinaria.dao.MascotaDao;
import com.veterinaria.modelo.Mascota;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Servlet encargado de mostrar el formulario "Nueva Mascota" con los
 * ComboBox de especie, veterinario y dueño (GET) y de procesar el
 * registro de la nueva mascota (POST). Pregunta 3.
 */
@WebServlet("/nuevaMascota")
public class NuevaMascotaServlet extends HttpServlet {

    private final MascotaDao mascotaDao = new MascotaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datos para llenar los ComboBox del formulario
        request.setAttribute("listaEspecies", mascotaDao.listarEspecies());
        request.setAttribute("listaVeterinarios", mascotaDao.listarVeterinarios());
        request.setAttribute("listaDuenos", mascotaDao.listarDuenos());

        request.getRequestDispatcher("/nuevaMascota.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Mascota mascota = new Mascota();
            mascota.setNombre(request.getParameter("nombre"));
            mascota.setEdad(Integer.parseInt(request.getParameter("edad")));
            mascota.setPeso(new BigDecimal(request.getParameter("peso")));
            mascota.setEspecieId(Integer.parseInt(request.getParameter("especieId")));
            mascota.setVeterinarioId(Integer.parseInt(request.getParameter("veterinarioId")));
            mascota.setDuenoId(Integer.parseInt(request.getParameter("duenoId")));

            mascotaDao.crear(mascota);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // En un escenario real se mostraría un mensaje de error al usuario
        }

        // Redirige al listado principal
        response.sendRedirect("mascotas");
    }
}
