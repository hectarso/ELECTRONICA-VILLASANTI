<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setNombre_establecimiento(nombre_establecimiento);

    EstablecimientosControlador.buscarEstablecimiento(establecimiento);
    if (establecimiento.getId_establecimiento() == 0) {
        tipo = "success";
        mensaje = "Datos de nombre repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_establecimiento", establecimiento.getNombre_establecimiento());

    out.print(obj);
    out.flush();
%>