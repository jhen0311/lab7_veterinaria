<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.veterinaria.modelo.Especie" %>
<%@ page import="com.veterinaria.modelo.Veterinario" %>
<%@ page import="com.veterinaria.modelo.Dueno" %>
<%@ page import="java.util.List" %>
<%
    List<Especie> listaEspecies = (List<Especie>) request.getAttribute("listaEspecies");
    List<Veterinario> listaVeterinarios = (List<Veterinario>) request.getAttribute("listaVeterinarios");
    List<Dueno> listaDuenos = (List<Dueno>) request.getAttribute("listaDuenos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Mascota</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

<h2>Nueva Mascota</h2>

<form action="nuevaMascota" method="post" class="form-mascota">

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required>

    <label for="edad">Edad:</label>
    <input type="number" id="edad" name="edad" min="0" required>

    <label for="peso">Peso (kg):</label>
    <input type="number" id="peso" name="peso" step="0.01" min="0" required>

    <label for="especieId">Especie:</label>
    <select id="especieId" name="especieId" required>
        <option value="">Seleccione una especie</option>
        <%
            if (listaEspecies != null) {
                for (Especie especie : listaEspecies) {
        %>
        <option value="<%= especie.getIdespecie() %>"><%= especie.getNombre() %></option>
        <%
                }
            }
        %>
    </select>

    <label for="veterinarioId">Veterinario:</label>
    <select id="veterinarioId" name="veterinarioId" required>
        <option value="">Seleccione un veterinario</option>
        <%
            if (listaVeterinarios != null) {
                for (Veterinario veterinario : listaVeterinarios) {
        %>
        <option value="<%= veterinario.getIdveterinario() %>"><%= veterinario.getNombre() %></option>
        <%
                }
            }
        %>
    </select>

    <label for="duenoId">Dueño:</label>
    <select id="duenoId" name="duenoId" required>
        <option value="">Seleccione un dueño</option>
        <%
            if (listaDuenos != null) {
                for (Dueno dueno : listaDuenos) {
        %>
        <option value="<%= dueno.getIddueno() %>"><%= dueno.getNombre() %></option>
        <%
                }
            }
        %>
    </select>

    <div class="acciones">
        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="mascotas" class="btn btn-secondary">Cancelar</a>
    </div>

</form>

</body>
</html>
