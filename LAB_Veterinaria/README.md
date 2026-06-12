# LAB_Veterinaria – IWEB 2026-1

Sistema web para gestión de mascotas de una veterinaria (Java EE: JSP + Servlet + DAO).

## Estructura del proyecto

```
LAB_Veterinaria/
├── pom.xml
├── database/
│   └── veterinaria.sql
└── src/main/
    ├── java/com/veterinaria/
    │   ├── modelo/      (Especie, Veterinario, Dueno, Mascota)
    │   ├── dao/          (DaoBase, MascotaDao)
    │   └── servlet/      (MascotaServlet, NuevaMascotaServlet, BorrarMascotaServlet)
    └── webapp/
        ├── index.jsp
        ├── listado.jsp
        ├── nuevaMascota.jsp
        ├── css/estilos.css
        └── WEB-INF/web.xml
```

## Mapeo con las preguntas del laboratorio

- **Pregunta 1 – DaoBase**: `dao/DaoBase.java`
  - `getConnection()` con configuración JDBC.
  - Métodos abstractos `crear(Object)` y `borrar(int)`.

- **Pregunta 2 – Listado de mascotas**: `dao/MascotaDao.java` (método `listar()`),
  `servlet/MascotaServlet.java`, vista `listado.jsp`.
  - Muestra nombre de especie, veterinario y dueño (no sus IDs).

- **Pregunta 3 – Crear y borrar**:
  - `MascotaDao.crear()` y `MascotaDao.borrar()` implementan los métodos abstractos de `DaoBase`.
  - `NuevaMascotaServlet` (GET carga combos, POST guarda).
  - `BorrarMascotaServlet` elimina por id.
  - ComboBox de especie, veterinario y dueño en `nuevaMascota.jsp`, cargados desde BD
    (`listarEspecies()`, `listarVeterinarios()`, `listarDuenos()`).

- **Pregunta 4 – Filtro por ComboBox**: `listado.jsp` incluye un `<select>` de especies
  que al cambiar (`onchange="this.form.submit()"`) recarga `MascotaServlet` con el
  parámetro `especieId`, filtrando la consulta en `MascotaDao.listar(int especieId)`.

## Configuración previa

1. Ejecutar el script `database/veterinaria.sql` en MySQL (crea la BD, tablas y datos de prueba).
2. En `DaoBase.java`, ajustar usuario/contraseña de MySQL si es necesario:
   ```java
   private static final String USUARIO = "root";
   private static final String PASSWORD = "root";
   ```
3. El proyecto usa Maven (`pom.xml`) con dependencias de Servlet API, JSTL y MySQL Connector/J.

## Despliegue

1. Importar el proyecto como **Maven Project** en Eclipse / IntelliJ / NetBeans.
2. Configurar un servidor **Apache Tomcat** (9 o 10) en el IDE.
3. Ejecutar `Run on Server`. La aplicación abrirá en:
   ```
   http://localhost:8080/LAB_Veterinaria/
   ```
   `index.jsp` redirige automáticamente a `mascotas` (listado principal).

## Pregunta 5 – Subir a GitHub

Desde la raíz del proyecto (`LAB_Veterinaria/`):

```bash
git init
git add .
git commit -m "Avance LAB7 IWEB: DaoBase, MascotaDao, CRUD y ComboBox"
git branch -M main
git remote add origin https://github.com/<tu-usuario>/LAB7_IWEB_<tu-codigo>.git
git push -u origin main
```

> Reemplaza `<tu-usuario>` por tu usuario de GitHub y `<tu-codigo>` por tu código de alumno,
> según el formato de nombre indicado en el enunciado (ej. `LAB8_IWEB_20191111`).
