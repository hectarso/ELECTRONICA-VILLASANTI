<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String ruc_cliente = request.getParameter("ruc_cliente");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Clientes cliente = new Clientes();
cliente.setRuc_cliente(ruc_cliente);

ClientesControlador.buscarRuc(cliente);
if(cliente.getId_cliente()== 0) {
    tipo = "success";
    mensaje = "Datos de ruc repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ruc_cliente", cliente.getRuc_cliente());


    out.print(obj);
    out.flush();
%>
