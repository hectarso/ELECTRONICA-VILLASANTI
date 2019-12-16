<%@page import="controladores.ClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import= "java.sql.ResultSet"%>
<%
    String nombre_cliente = request.getParameter("bnombre_cliente");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "busqueda exitosa.";
    String contenido = ClientesControlador.buscarNombre(nombre_cliente, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>