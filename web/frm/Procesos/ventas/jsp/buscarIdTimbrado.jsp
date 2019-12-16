<%@page import="modelos.Establecimientos"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    //int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    //String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    
    /*Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    establecimiento.setNombre_establecimiento(nombre_establecimiento);
    timbrado.setEstablecimiento(establecimiento);*/
    String mensaje = "datos no encontrados";
    timbrado = TimbradosControlador.buscarId(timbrado);
    //System.out.println("llega");
    if (timbrado.getId_timbrado() != 0) {
        mensaje = "datos encontrados";
    }
    String tipo = "error";
    String nuevo = "true";
    if (timbrado != null) {
        tipo = "success";
        mensaje = "datos encontrados.";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_timbrado", timbrado.getId_timbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    /*obj.put("id_establecimiento", timbrado.getEstablecimiento().getId_establecimiento());
    obj.put("nombre_establecimiento", timbrado.getEstablecimiento().getNombre_establecimiento());*/
    out.print(obj);
    out.flush();
%>