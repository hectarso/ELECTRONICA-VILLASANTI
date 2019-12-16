<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String ruc_establecimiento = request.getParameter("ruc_establecimiento");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setRuc_establecimiento(ruc_establecimiento);

    EstablecimientosControlador.buscarRuc(establecimiento);
    if (establecimiento.getId_establecimiento() == 0) {
        tipo = "success";
        mensaje = "Datos de Ruc repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ruc_establecimiento", establecimiento.getRuc_establecimiento());

    out.print(obj);
    out.flush();
%>
