<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String nombre_proveedor = request.getParameter("nombre_proveedor");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setNombre_proveedor(nombre_proveedor);

ProveedoresControlador.buscarProveedor(proveedor);
if(proveedor.getId_proveedor()== 0) {
    tipo = "success";
    mensaje = "Datos de Nombre repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_proveedor", proveedor.getNombre_proveedor());


    out.print(obj);
    out.flush();
%>
