<%@page import="controladores.FacturaDetalleComprasControlador"%>
<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.Proveedores"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));
     String sfecha_factura_compra = request.getParameter("fecha_factura_compra");
    java.sql.Date fecha_factura_compra = Utiles.stringToSqlDate(sfecha_factura_compra);
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_compra = request.getParameter("ruc_factura_compra");
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    FacturaCompras facturacompra = FacturaComprasControlador.buscarId(id_factura_compra);
    if (facturacompra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        facturacompra = new FacturaCompras();
        facturacompra.setId_factura_compra(0);
        facturacompra.setFecha_factura_compra(fecha_factura_compra);
  
        Proveedores proveedor = new Proveedores();
        facturacompra.setProveedor(proveedor);
        
        Tipo_facturas tipofactura = new Tipo_facturas();
        facturacompra.setTipofactura(tipofactura);
        }
    
        
    
    String contenido_detalle = FacturaDetalleComprasControlador.buscarIdFacturaCompra(id_factura_compra);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura_compra", facturacompra.getId_factura_compra());
    obj.put("fecha_factura_compra", String.valueOf(facturacompra.getFecha_factura_compra()));
    obj.put("id_proveedor", facturacompra.getProveedor().getId_proveedor());
    obj.put("nombre_proveedor", facturacompra.getProveedor().getNombre_proveedor());
    obj.put("ruc_proveedor", facturacompra.getProveedor().getRuc_proveedor());
    obj.put("id_tipo_factura", facturacompra.getTipofactura().getId_tipo_factura());
    obj.put("nombre_tipo_factura", facturacompra.getTipofactura().getNombre_tipo_factura());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>