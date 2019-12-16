<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="modelos.Productos"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalle_venta = Integer.parseInt(request.getParameter("id_detalle_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int cantidad_producto_venta = Integer.parseInt(request.getParameter("cantidad_producto_venta"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalle_venta(id_detalle_venta);
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_producto_venta);
    stock.setProducto(producto);
    StocksControlador.sumar(stock);

    if (DetallesVentasControlador.eliminar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>