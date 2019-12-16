<%@page import="modelos.Timbrados"%>
<%@page  import="controladores.TimbradosControlador"%>
<%@page  import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String numero_timbrado = request.getParameter("numero_timbrado");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setNumero_timbrado(numero_timbrado);

    TimbradosControlador.buscarTimbrado(timbrado);
    if (timbrado.getId_timbrado() == 0) {
        tipo = "success";
        mensaje = "Datos de Numero repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());

    out.print(obj);
    out.flush();
%>