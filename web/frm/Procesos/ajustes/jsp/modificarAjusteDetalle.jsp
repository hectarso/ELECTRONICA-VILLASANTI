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
    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_ajuste_stock_detalle(id_ajuste_stock_detalle);
    detalleajuste.setCantidad_ajuste_stock(cantidad_ajuste_stock);
    
    
    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);
    
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setProducto(producto);
    if (DetallesAjustesControlador.modificar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>