<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String direccion_establecimiento = request.getParameter("direccion_establecimiento");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setDireccion_establecimiento(direccion_establecimiento);

    EstablecimientosControlador.buscarDireccion(establecimiento);
    if (establecimiento.getId_establecimiento() == 0) {
        tipo = "success";
        mensaje = "Datos de direccion repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("direccion_establecimiento", establecimiento.getDireccion_establecimiento());

    out.print(obj);
    out.flush();
%>