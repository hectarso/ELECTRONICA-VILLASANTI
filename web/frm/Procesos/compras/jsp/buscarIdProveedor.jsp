<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_proveedor =Integer.parseInt(request.getParameter("id_proveedor"));
    String nombre_proveedor =request.getParameter("nombre_proveedor");
    String ruc_proveedor =request.getParameter("ruc_proveedor");
    Proveedores proveedor= new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    proveedor.setNombre_proveedor(nombre_proveedor);
    proveedor.setRuc_proveedor(ruc_proveedor);
    String mensaje= "Datos no encontrados";
    proveedor= ProveedoresControlador.buscarId(proveedor);
    //System.out.println("llega");
    if (proveedor.getId_proveedor()!=0){
        mensaje ="Datos encontrados";
    }
    String tipo="error";
    String nuevo="true";
    if (proveedor!=null){
        tipo= "success";
        mensaje= "Datos encontrados.";
        nuevo= "false";
    }
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_proveedor",proveedor.getId_proveedor());
    obj.put("nombre_proveedor",proveedor.getNombre_proveedor());
    obj.put("ruc_proveedor",proveedor.getRuc_proveedor());
    //obj.put("id_proveedor",proveedor.getProveedor(),getId_proveedor());
    //obj.put("nombre_proveedor",proveedor.getProveedor().getNombre_proveedor());
    out.print(obj);
    out.flush();
    %>