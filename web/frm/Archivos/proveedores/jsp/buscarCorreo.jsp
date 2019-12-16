<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String correo_proveedor = request.getParameter("correo_proveedor");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setCorreo_proveedor(correo_proveedor);

ProveedoresControlador.buscarCorreo(proveedor);
if(proveedor.getId_proveedor()== 0) {
    tipo = "success";
    mensaje = "Datos de correo repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("correo_proveedor", proveedor.getCorreo_proveedor());


    out.print(obj);
    out.flush();
%>