<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="modelos.FacturaDetalleCompras"%>
<%@page import="modelos.Productos"%>
<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_detalle_compra = Integer.parseInt(request.getParameter("id_factura_detalle_compra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaDetalleCompras facturadetallecompra = FacturaDetalleComprasControlador.buscarId(id_factura_detalle_compra);
    if (facturadetallecompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturadetallecompra = new FacturaDetalleCompras();
        facturadetallecompra.setId_factura_detalle_compra(0);
        //facturadetallecompra.setSubtotal_compra(0);
        
        FacturaCompras facturacompra = new FacturaCompras();
        facturacompra.setId_factura_compra(0);
        facturadetallecompra.setFacturacompra(facturacompra);
        
        Productos producto = new Productos();
        producto.setId_producto(0);
        producto.setNombre_producto("");
        facturadetallecompra.setProducto(producto);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura_detalle_compra", String.valueOf(facturadetallecompra.getId_factura_detalle_compra()));
    obj.put("id_factura_compra", String.valueOf(facturadetallecompra.getFacturacompra().getId_factura_compra()));
    obj.put("id_producto", String.valueOf(facturadetallecompra.getProducto().getId_producto()));
    obj.put("nombre_producto", facturadetallecompra.getProducto().getNombre_producto());
    obj.put("precio_compra_producto", facturadetallecompra.getProducto().getPrecio_compra_producto());
    obj.put("cantidad_compra", String.valueOf(facturadetallecompra.getCantidad_compra()));
    //obj.put("subtotal_compra", String.valueOf(facturadetallecompra.getSubtotal_compra()));
    
    out.print(obj);
    out.flush();
%>