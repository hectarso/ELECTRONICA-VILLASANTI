
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    ProductosControlador.buscarId(producto);
    if (producto.getId_producto() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    obj.put("id_marca", producto.getMarca().getId_marca());
    obj.put("nombre_marca", producto.getMarca().getNombre_marca());
    obj.put("id_categoria", producto.getCategoria().getId_categoria());
    obj.put("nombre_categoria", producto.getCategoria().getNombre_categoria());
    obj.put("id_ubicacion", producto.getUbicacion().getId_ubicacion());
    obj.put("nombre_ubicacion", producto.getUbicacion().getNombre_ubicacion());
    obj.put("stockmin_producto", producto.getStockmin_producto());
    obj.put("stockmax_producto", producto.getStockmax_producto());
    obj.put("codigo_producto", producto.getCodigo_producto());
    obj.put("precio_compra_producto", producto.getPrecio_compra_producto());
    obj.put("precio_venta_producto", producto.getPrecio_venta_producto());
    obj.put("iva_producto", producto.getIva_producto());

    out.print(obj);
    out.flush();
%>