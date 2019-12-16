
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_proveedor = request.getParameter("nombre_proveedor");
    String ruc_proveedor = request.getParameter("ruc_proveedor");
    String direccion_proveedor = request.getParameter("direccion_proveedor");
    String correo_proveedor = request.getParameter("correo_proveedor");
    String telefono1_proveedor = request.getParameter("telefono1_proveedor");
    String telefono2_proveedor = request.getParameter("telefono2_proveedor");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    proveedor.setNombre_proveedor(nombre_proveedor);
    proveedor.setRuc_proveedor(ruc_proveedor);
    proveedor.setDireccion_proveedor(direccion_proveedor);
    proveedor.setCorreo_proveedor(correo_proveedor);
    proveedor.setTelefono1_proveedor(telefono1_proveedor);
    proveedor.setTelefono2_proveedor(telefono2_proveedor);
    proveedor.setCiudad(ciudad);

    if (ProveedoresControlador.agregar(proveedor)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
