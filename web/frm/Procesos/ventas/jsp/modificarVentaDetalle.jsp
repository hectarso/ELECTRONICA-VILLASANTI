<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalle_venta = Integer.parseInt(request.getParameter("id_detalle_venta"));
    int cantidad_producto_venta = Integer.parseInt(request.getParameter("cantidad_producto_venta"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int preciototal_venta = Integer.parseInt(request.getParameter("preciototal_venta"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalle_venta(id_detalle_venta);
    detalleventa.setCantidad_producto_venta(cantidad_producto_venta);
    detalleventa.setPreciototal_venta(preciototal_venta);

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    detalleventa.setVenta(venta);
    detalleventa.setProducto(producto);

    if (DetallesVentasControlador.modificar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>