<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_ajuste_stock_detalle = Integer.parseInt(request.getParameter("id_ajuste_stock_detalle"));
    int cantidad_ajuste_stock = Integer.parseInt(request.getParameter("cantidad_ajuste_stock"));
    int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    // int total_ajuste= con + 0; 

    //int con=total_detalleajuste;
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_ajuste_stock_detalle(id_ajuste_stock_detalle);
    detalleajuste.setCantidad_ajuste_stock(cantidad_ajuste_stock);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setProducto(producto);
    
    Stocks stock = new Stocks();
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setProducto(producto);
    StocksControlador.sumar(stock);

    if (DetallesAjustesControlador.agregar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    producto = new Productos();
    producto.setId_producto(id_producto); //el de abajo puede ser un problema
   // ProductosControlador.modificara(producto);

    /*Stock stock = new Stock();
    stock.setCantidad_stock(cantidad_ajuste_stock);
    stock.setProducto(producto);
    StockControlador.sumar(stock);*/

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>