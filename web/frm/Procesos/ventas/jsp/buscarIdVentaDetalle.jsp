<%@page import="modelos.Ivas"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalle_venta = Integer.parseInt(request.getParameter("id_detalle_venta"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesVentas detalleventa = DetallesVentasControlador.buscarId(id_detalle_venta);
    if (detalleventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleventa = new DetallesVentas();
        detalleventa.setId_detalle_venta(0);
        detalleventa.setPreciototal_venta(0);

        Ventas venta = new Ventas();
        venta.setId_venta(0);
        detalleventa.setVenta(venta);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setCodigo_producto("");
        producto.setIva_producto(0);
        //producto.setPrecio_venta_producto(0);
        detalleventa.setProducto(producto);
        
        /*Ivas iva = new Ivas();
        iva.setPorcentaje_iva(0);
        producto.setIva(iva);*/
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalle_venta", String.valueOf(detalleventa.getId_detalle_venta()));
    obj.put("id_venta", String.valueOf(detalleventa.getVenta().getId_venta()));
    obj.put("id_producto", String.valueOf(detalleventa.getProducto().getId_producto()));
    obj.put("nombre_producto", detalleventa.getProducto().getNombre_producto());
    obj.put("codigo_producto", detalleventa.getProducto().getCodigo_producto());
    obj.put("precio_venta_producto", detalleventa.getProducto().getPrecio_venta_producto());
    obj.put("iva_producto", detalleventa.getProducto().getIva_producto());
    /*obj.put("nombre_iva", detalleventa.getProducto().getIva().getNombre_iva());
    obj.put("porcentaje_iva", detalleventa.getProducto().getIva().getPorcentaje_iva());
    obj.put("nombre_marca", String.valueOf(detalleventa.getProducto().getMarca().getNombre_marca()));*/
    obj.put("cantidad_producto_venta", String.valueOf(detalleventa.getCantidad_producto_venta()));
    obj.put("preciototal_venta", String.valueOf(detalleventa.getPreciototal_venta()));

    out.print(obj);
    out.flush();
%>