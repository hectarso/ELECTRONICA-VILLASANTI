<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String telefono2_proveedor = request.getParameter("telefono2_proveedor");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setTelefono2_proveedor(telefono2_proveedor);

ProveedoresControlador.buscarTelefono2(proveedor);
if(proveedor.getId_proveedor()== 0) {
    tipo = "success";
    mensaje = "Datos de telefono2 repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("telefono2_proveedor", proveedor.getTelefono2_proveedor());


    out.print(obj);
    out.flush();
%>