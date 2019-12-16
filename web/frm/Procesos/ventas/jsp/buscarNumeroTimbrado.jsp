<%@page import="controladores.TimbradosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import= "java.sql.ResultSet"%>
<%
    String numero_timbrado = request.getParameter("bnumero_timbrado");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "busqueda exitosa.";
    String contenido = TimbradosControlador.buscarNumero(numero_timbrado, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>