<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  String ruc_proveedor = request.getParameter("ruc_proveedor");  
  String tipo = "error";
  String mensaje = "Datos no repetidos";
  String nuevo = "true";
Proveedores proveedor = new Proveedores();
proveedor.setRuc_proveedor(ruc_proveedor);

ProveedoresControlador.buscarRuc(proveedor);
if(proveedor.getId_proveedor()== 0) {
    tipo = "success";
    mensaje = "Datos de Ruc repetidos";
    nuevo = "false";
}
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("ruc_proveedor", proveedor.getRuc_proveedor());


    out.print(obj);
    out.flush();
%>
