<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="modelos.FacturaCompras"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    int id_tipo_factura = Integer.parseInt(request.getParameter("id_tipo_factura"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(id_tipo_factura);

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);
    facturacompra.setProveedor(proveedor);
    facturacompra.setTipofactura(tipofactura);
   
    if (FacturaComprasControlador.modificar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>