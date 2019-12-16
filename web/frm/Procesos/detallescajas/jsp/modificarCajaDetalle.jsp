
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe_caja = Integer.parseInt(request.getParameter("importe_caja"));
  //  int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
  //  int costo_cuenta = Integer.parseInt(request.getParameter("costo_cuenta"));    
  //  int total_detallecaja = cantidad_cuentaventa * costo_cuenta;
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setImporte_caja(importe_caja);
   
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
   /* Cajas cuenta = new Cajas();
    cuenta.setId_cuenta(id_cuenta);*/
    
    detallecaja.setVenta(venta);
   // detallecaja.setCuenta(cuenta);
    
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