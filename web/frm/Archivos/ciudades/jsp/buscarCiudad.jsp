<%@page import="modelos.Ciudades"%>
<%@page import="controladores.CiudadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_ciudad = request.getParameter("nombre_ciudad");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Ciudades ciudad = new Ciudades();
    ciudad.setNombre_ciudad(nombre_ciudad);

    CiudadesControlador.buscarCiudad(ciudad);
    if (ciudad.getId_ciudad() == 0) {
        tipo = "success";
        mensaje = "Datos de Nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_ciudad", ciudad.getNombre_ciudad());

    out.print(obj);
    out.flush();
%>