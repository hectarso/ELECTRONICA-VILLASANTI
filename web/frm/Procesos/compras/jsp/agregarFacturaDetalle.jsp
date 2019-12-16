<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="modelos.FacturaDetalleCompras"%>

<%@page import="modelos.Productos"%>
<%--<%@page import="modelos.Pedidos"%>--%>
<%--<%@page import="modelos.DetallesPedidos"%>--%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));

    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int cantidad_compra = Integer.parseInt(request.getParameter("cantidad_compra"));
    //int subtotal_compra = Integer.parseInt(request.getParameter("subtotal_compra"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturaDetalleCompras facturadetallecompra = new FacturaDetalleCompras();
    //detallepedido.setId_detallepedido(id_detallepedido);
    facturadetallecompra.setCantidad_compra(cantidad_compra);
    //facturadetallecompra.setSubtotal_compra(subtotal_compra);

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    facturadetallecompra.setFacturacompra(facturacompra);
    facturadetallecompra.setProducto(producto);
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_compra);
    stock.setProducto(producto);
    StocksControlador.sumar(stock);
    if (FacturaDetalleComprasControlador.agregar(facturadetallecompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>