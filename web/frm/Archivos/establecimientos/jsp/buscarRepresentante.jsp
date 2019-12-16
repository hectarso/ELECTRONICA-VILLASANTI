<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String representante_establecimiento = request.getParameter("representante_establecimiento");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setRepresentante_establecimiento(representante_establecimiento);

    EstablecimientosControlador.buscarRepresentante(establecimiento);
    if (establecimiento.getId_establecimiento() == 0) {
        tipo = "success";
        mensaje = "Datos de representante repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("representante_establecimiento", establecimiento.getRepresentante_establecimiento());

    out.print(obj);
    out.flush();
%>
