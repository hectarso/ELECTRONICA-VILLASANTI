<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock_detalle = Integer.parseInt(request.getParameter("id_ajuste_stock_detalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesAjustes detalleajuste = DetallesAjustesControlador.buscarId(id_ajuste_stock_detalle);
    if (detalleajuste != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleajuste = new DetallesAjustes();
        detalleajuste.setId_ajuste_stock_detalle(0);
        detalleajuste.setCantidad_ajuste_stock(0);

        Ajustes ajuste = new Ajustes();
        ajuste.setId_ajuste_stock(0);
        detalleajuste.setAjuste(ajuste);

        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        producto.setPrecio_compra_producto(0);
        detalleajuste.setProducto(producto);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_ajuste_stock_detalle", detalleajuste.getId_ajuste_stock_detalle());
    obj.put("id_ajuste_stock", detalleajuste.getAjuste().getId_ajuste_stock());
    obj.put("id_producto", detalleajuste.getProducto().getId_producto());
    System.out.println("id"+detalleajuste.getProducto().getId_producto());
    obj.put("nombre_producto", detalleajuste.getProducto().getNombre_producto());
    System.out.println("nombre"+detalleajuste.getProducto().getNombre_producto());
    obj.put("precio_compra_producto", detalleajuste.getProducto().getPrecio_compra_producto());
   System.out.println("preciocompra"+detalleajuste.getProducto().getPrecio_compra_producto());
    obj.put("cantidad_ajuste_stock", detalleajuste.getCantidad_ajuste_stock());
    out.print(obj);
    out.flush();
%>