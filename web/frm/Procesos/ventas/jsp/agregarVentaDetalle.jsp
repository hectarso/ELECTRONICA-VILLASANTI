
<%@page import="controladores.UbicacionesControlador"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="modelos.Productos"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_detalle_venta = Integer.parseInt(request.getParameter("id_detalle_venta"));
    int cantidad_producto_venta = Integer.parseInt(request.getParameter("cantidad_producto_venta"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int preciototal_venta = Integer.parseInt(request.getParameter("preciototal_venta"));
    /*int subtotal_5 = Integer.parseInt(request.getParameter("ssubtotal_5"));
    int subtotal_10 = Integer.parseInt(request.getParameter("ssubtotal_10"));
    int subtotal_exenta = Integer.parseInt(request.getParameter("ssubtotal_exenta"));
    int totalgravada_5 = Integer.parseInt(request.getParameter("ttotalgravada_5"));
    int totalgravada_10 = Integer.parseInt(request.getParameter("ttotalgravada_10"));*/

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesVentas detalleventa = new DetallesVentas();
    //detalleventa.setId_detalle_venta(id_detalle_venta);
    detalleventa.setCantidad_producto_venta(cantidad_producto_venta);
    detalleventa.setPreciototal_venta(preciototal_venta);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    
    /*Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_producto);
    producto.setUbicacion(ubicacion);*/

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    /*venta.setSubtotal_10(subtotal_10);
    venta.setSubtotal_5(subtotal_5);
    venta.setSubtotal_exenta(subtotal_exenta);
    venta.setTotalgravada_10(totalgravada_10);
    venta.setTotalgravada_5(totalgravada_5);
    VentasControlador.totalgravadas(venta);
    VentasControlador.subtotaliva(venta);*/
    

    detalleventa.setVenta(venta);
    detalleventa.setProducto(producto);

    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_producto_venta);
    stock.setProducto(producto);
    StocksControlador.descontar(stock);
    if (stock.getCantidad_stock() != -1) {
        if (DetallesVentasControlador.agregar(detalleventa)) {
            tipo = "success";
            mensaje = "Datos agregados.";
        }
    } else {
        tipo = "success";
        mensaje = "Stock insuficiente";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();
%>
