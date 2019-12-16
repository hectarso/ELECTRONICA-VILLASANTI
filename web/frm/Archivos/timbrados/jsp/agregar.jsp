

<%@page import="modelos.Establecimientos"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sfechaini_timbrado = request.getParameter("fechaini_timbrado");
    java.sql.Date fechaini_timbrado = Utiles.stringToSqlDate(sfechaini_timbrado);
    String sfechaact_timbrado = request.getParameter("fechaact_timbrado");
    java.sql.Date fechaact_timbrado = Utiles.stringToSqlDate(sfechaact_timbrado);
    String sfechafin_timbrado = request.getParameter("fechafin_timbrado");
    java.sql.Date fechafin_timbrado = Utiles.stringToSqlDate(sfechafin_timbrado);
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    String estado_timbrado = request.getParameter("estado_timbrado");
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);

    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFechaini_timbrado(fechaini_timbrado);
    timbrado.setFechaact_timbrado(fechaact_timbrado);
    timbrado.setFechafin_timbrado(fechafin_timbrado);
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);
    timbrado.setEstado_timbrado(estado_timbrado);
    timbrado.setEstablecimiento(establecimiento);

    if (TimbradosControlador.agregar(timbrado)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
