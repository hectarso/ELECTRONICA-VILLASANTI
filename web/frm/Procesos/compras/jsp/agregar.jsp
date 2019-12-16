<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="utiles.Utiles"%>


<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));
    //String ruc_factura_compra = (request.getParameter("ruc_factura_compra"));

    
    String sfecha_factura_compra = request.getParameter("fecha_factura_compra");
    java.sql.Date fecha_factura_compra = Utiles.stringToSqlDate(sfecha_factura_compra);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(id_tipo_factura);
        
    
    /*Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(id_tipo_factura);*/
   
    
    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);
    //facturacompra.setRuc_factura_compra(ruc_factura_compra);
    facturacompra.setFecha_factura_compra(fecha_factura_compra);
    facturacompra.setProveedor(proveedor);
    facturacompra.setTipofactura(tipofactura);
    //facturacompra.setTipofactura(tipofactura);
      
    
    if (FacturaComprasControlador.agregar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_factura_compra", String.valueOf(facturacompra.getId_factura_compra()));
    out.print(obj);
    out.flush();
    
%>