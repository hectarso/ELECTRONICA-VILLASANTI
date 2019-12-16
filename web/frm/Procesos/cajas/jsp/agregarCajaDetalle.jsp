<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   // int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe_caja = Integer.parseInt(request.getParameter("importe_caja"));
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    //int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    int total = Integer.parseInt(request.getParameter("total"));
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    String forma_pago = request.getParameter("forma_pago");
    int totald = importe_caja - total;
    String tipo = "error";

    String mensaje = "Datos no agregados.";

    DetallesCajas detallecaja = new DetallesCajas();
    //detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setVuelto(totald);
    detallecaja.setImporte_caja(importe_caja);
    //  detallecaja.setCantidad_cajaventa(cantidad_cajaventa);

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    //facturaventa.setNumero_factura_venta(numero_factura_venta);
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);

    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);
    detallecaja.setVenta(venta);
    detallecaja.setCaja(caja);
    detallecaja.setPago(pago);

    VentasControlador.modificarestadocobro(venta);

    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setNumero_factura(0);
    pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>