
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);

    ProveedoresControlador.buscarId(proveedor);
    if (proveedor.getId_proveedor() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_proveedor", proveedor.getId_proveedor());
    obj.put("nombre_proveedor", proveedor.getNombre_proveedor());
    obj.put("ruc_proveedor", proveedor.getRuc_proveedor());
    obj.put("direccion_proveedor", proveedor.getDireccion_proveedor());
    obj.put("id_ciudad", proveedor.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", proveedor.getCiudad().getNombre_ciudad());
    obj.put("correo_proveedor", proveedor.getCorreo_proveedor());
    obj.put("telefono1_proveedor", proveedor.getTelefono1_proveedor());
    obj.put("telefono2_proveedor", proveedor.getTelefono2_proveedor());

    out.print(obj);
    out.flush();
%>