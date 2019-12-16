
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
  //  int costo_caja = Integer.parseInt(request.getParameter("costo_caja"));    
  //  int total_detallecaja = cantidad_cajaventa * costo_caja;
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setImporte_caja(importe);
   
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    detallecaja.setVenta(venta);
    detallecaja.setCaja(caja);
    
    if (DetallesCajasControlador.modificar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>