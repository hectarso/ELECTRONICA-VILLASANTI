
<%@page import="modelos.Ivas"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String nombre_producto = request.getParameter("nombre_producto");
    String codigo_producto =request.getParameter("codigo_producto");
    int precio_venta_producto = Integer.parseInt(request.getParameter("precio_venta_producto"));
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    //String nombre_iva = request.getParameter("nombre_iva");
    //int porcentaje_iva = Integer.parseInt(request.getParameter("porcentaje_iva"));
    

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setNombre_producto(nombre_producto);
    producto.setCodigo_producto(codigo_producto);
    producto.setPrecio_venta_producto(precio_venta_producto);
    producto.setIva_producto(iva_producto);
    
    /*Ivas iva = new Ivas();
    iva.setPorcentaje_iva(porcentaje_iva);
    iva.setNombre_iva(nombre_iva);
    producto.setIva(iva);*/

    String mensaje = "datos no encontrados";
    producto = ProductosControlador.buscarId(producto);
    System.out.println("llega");
    if (producto.getId_producto() != 0) {
        mensaje = "datos encontrados";
    }
    String tipo = "error";
    String nuevo = "true";
    if (producto != null) {
        tipo = "success";
        mensaje = "datos encontrados.";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    //obj.put("nombre_marca", String.valueOf(producto.getMarca().getNombre_marca()));
    obj.put("codigo_producto",producto.getCodigo_producto());
    obj.put("precio_venta_producto", producto.getPrecio_venta_producto());
    obj.put("iva_producto", producto.getIva_producto());
    /*obj.put("nombre_producto",producto.getNombre_producto());
    obj.put("id_iva",producto.getIva().getId_iva());
    obj.put("nombre_iva",producto.getIva().getNombre_iva());
    obj.put("porcentaje_iva",producto.getIva().getPorcentaje_iva());*/
    out.print(obj);
    out.flush();
%>