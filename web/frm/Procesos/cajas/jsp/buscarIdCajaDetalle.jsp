
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesCajas detallecaja = DetallesCajasControlador.buscarId(id_detallecaja);
    if (detallecaja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecaja = new DetallesCajas();
        detallecaja.setId_detallecaja(0);
        detallecaja.setImporte_caja(0);

        Cajas caja = new Cajas();
        caja.setId_caja(0);
        detallecaja.setCaja(caja);

        Ventas venta = new Ventas();
        venta.setId_venta(0);
        //venta.setNumero_factura(0);
        detallecaja.setVenta(venta);

        Pagos pago = new Pagos();
        pago.setId_pago(0);
        pago.setForma_pago("");
        detallecaja.setPago(pago);
    }
    //int id_factura_venta = detallecaja.getFacturaventa().getId_factura_venta();
    //System.out.println("idfactu "+ id_factura_venta);
    //FacturaVentas facturaventa = new FacturaVentas();
    //FacturaVentas facturaventa = FacturaVentasControlador.buscarTotalfactura(id_factura_venta);
    //int total1 = facturaventa.getTotal();
    //System.out.println("total1 "+ total1);
    //int importe = detallecaja.getImporte();
    //int vuelto1 = importe - total1;

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detallecaja", String.valueOf(detallecaja.getId_detallecaja()));
    obj.put("id_caja", String.valueOf(detallecaja.getCaja().getId_caja()));
    System.out.println("cajaid= "+String.valueOf(detallecaja.getCaja().getId_caja()));
    obj.put("id_venta", String.valueOf(detallecaja.getVenta().getId_venta()));
    obj.put("id_pago", String.valueOf(detallecaja.getPago().getId_pago()));
    //obj.put("numero_factura", String.valueOf(detallecaja.getVenta().getNumero_factura()));
    obj.put("importe_caja", String.valueOf(detallecaja.getImporte_caja()));
    //obj.put("vuelto", (vuelto1));
    //obj.put("total", facturaventa.getTotal());
    //System.out.println("total "+ facturaventa.getTotal());
    //System.out.println("vuelto "+ vuelto1);
    out.print(obj);
    out.flush();
%>
