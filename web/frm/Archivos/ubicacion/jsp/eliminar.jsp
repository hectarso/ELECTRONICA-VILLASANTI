<%@page import="controladores.UbicacionesControlador"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ubicacion = Integer.parseInt(request.getParameter("id_ubicacion"));
    String nombre_ubicacion = request.getParameter("nombre_ubicacion");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_ubicacion);
    ubicacion.setNombre_ubicacion(nombre_ubicacion);

    if (UbicacionesControlador.eliminar(ubicacion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
