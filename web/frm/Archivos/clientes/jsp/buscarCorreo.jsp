<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String correo_cliente = request.getParameter("correo_cliente");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Clientes cliente = new Clientes();
cliente.setCorreo_cliente(correo_cliente);

ClientesControlador.buscarCorreo(cliente);
if(cliente.getId_cliente()== 0) {
    tipo = "success";
    mensaje = "Datos de correo repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("correo_cliente", cliente.getCorreo_cliente());


    out.print(obj);
    out.flush();
%>
