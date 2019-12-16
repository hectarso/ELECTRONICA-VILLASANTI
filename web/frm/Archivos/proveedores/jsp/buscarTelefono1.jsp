<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String telefono1_proveedor = request.getParameter("telefono1_proveedor");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setTelefono1_proveedor(telefono1_proveedor);

ProveedoresControlador.buscarTelefono1(proveedor);
if(proveedor.getId_proveedor()== 0) {
    tipo = "success";
    mensaje = "Datos de telefono1 repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("telefono1_proveedor", proveedor.getTelefono1_proveedor());


    out.print(obj);
    out.flush();
%>