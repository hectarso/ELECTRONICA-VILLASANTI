<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String ci_cliente = request.getParameter("ci_cliente");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Clientes cliente = new Clientes();
cliente.setCi_cliente(ci_cliente);

ClientesControlador.buscarCi(cliente);
if(cliente.getId_cliente()== 0) {
    tipo = "success";
    mensaje = "Datos de ci repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ci_cliente", cliente.getCi_cliente());


    out.print(obj);
    out.flush();
%>
