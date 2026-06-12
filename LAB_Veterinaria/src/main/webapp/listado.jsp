<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.veterinaria.modelo.Especie" %>
<%@ page import="com.veterinaria.modelo.Mascota" %>
<%@ page import="java.util.List" %>
<%
    List<Mascota> listaMascotas = (List<Mascota>) request.getAttribute("listaMascotas");
    List<Especie> listaEspecies = (List<Especie>) request.getAttribute("listaEspecies");
    Integer especieSeleccionada = (Integer) request.getAttribute("especieSeleccionada");
    if (especieSeleccionada == null) {
        especieSeleccionada = 0;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Mascotas</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<h2>Lista de Mascotas</h2>

<a href="nuevaMascota" class="btn btn-success">Nueva Mascota</a>

<form action="mascotas" method="get" class="filtro-form">
    <label for="especieId">Filtrar por especie:</label>
    <select name="especieId" id="especieId" onchange="this.form.submit()">
        <option value="0" <%= especieSeleccionada == 0 ? "selected" : "" %>>Todas las especies</option>
        <%
            if (listaEspecies != null) {
                for (Especie especie : listaEspecies) {
                    String selected = (especie.getIdespecie() == especieSeleccionada) ? "selected" : "";
        %>
        <option value="<%= especie.getIdespecie() %>" <%= selected %>><%= especie.getNombre() %></option>
        <%
                }
            }
        %>
    </select>
</form>

<table class="tabla-mascotas">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Peso</th>
            <th>Especie</th>
            <th>Veterinario</th>
            <th>Dueño</th>
            <th>Acción</th>
        </tr>
    </thead>
    <tbody>
        <%
            if (listaMascotas != null) {
                for (Mascota mascota : listaMascotas) {
        %>
        <tr>
            <td><%= mascota.getIdmascota() %></td>
            <td><%= mascota.getNombre() %></td>
            <td><%= mascota.getEdad() %></td>
            <td><%= mascota.getPeso() %></td>
            <td><%= mascota.getEspecie().getNombre() %></td>
            <td><%= mascota.getVeterinario().getNombre() %></td>
            <td><%= mascota.getDueno().getNombre() %></td>
            <td>
                <a href="borrarMascota?id=<%= mascota.getIdmascota() %>"
                   class="btn btn-danger"
                   onclick="return confirm('¿Está seguro de eliminar esta mascota?');">
                   Borrar
                </a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </tbody>
</table>

</body>
</html>
