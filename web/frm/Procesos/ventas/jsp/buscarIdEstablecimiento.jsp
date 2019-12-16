<%@page import="modelos.Ciudades"%>
<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    //String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    //String ruc_establecimiento = request.getParameter("ruc_establecimiento");

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    //establecimiento.setNombre_establecimiento(nombre_establecimiento);
    //establecimiento.setRuc_establecimiento(ruc_establecimiento);

    String mensaje = "datos no encontrados";
    establecimiento = EstablecimientosControlador.buscarId(establecimiento);
    System.out.println("llega");
    if (establecimiento.getId_establecimiento() != 0) {
        mensaje = "datos encontrados";
    }
    String tipo = "error";
    String nuevo = "true";
    if (establecimiento != null) {
        tipo = "success";
        mensaje = "datos encontrados.";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_establecimiento", establecimiento.getId_establecimiento());
    /*obj.put("nombre_establecimiento", establecimiento.getNombre_establecimiento());
    obj.put("ruc_establecimiento", establecimiento.getRuc_establecimiento());*/
    out.print(obj);
    out.flush();
%>