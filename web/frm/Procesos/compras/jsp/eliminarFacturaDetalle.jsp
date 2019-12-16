<%@page import="modelos.Productos"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="modelos.FacturaDetalleCompras"%>
<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_compra = Integer.parseInt(request.getParameter("id_factura_detalle_compra"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    facturadetallecompra.setId_factura_detalle_compra(id_factura_detalle_compra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_compra);
    stock.setProducto(producto);
    StocksControlador.descontar(stock);
    if (FacturaDetalleComprasControlador.eliminar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>