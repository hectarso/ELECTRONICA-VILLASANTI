
<%@page import="controladores.UbicacionesControlador"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ubicacion = Integer.parseInt(request.getParameter("id_ubicacion"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_ubicacion);

    UbicacionesControlador.buscarId(ubicacion);
    if (ubicacion.getId_ubicacion() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        ubicacion = new Ubicaciones();
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_ubicacion", ubicacion.getId_ubicacion());
    obj.put("nombre_ubicacion", ubicacion.getNombre_ubicacion());

    out.print(obj);
    out.flush();
%>