
<%@page import="modelos.Establecimientos"%>
<%@page import="groovy.sql.Sql"%>
<%@page import="modelos.Timbrados"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   
   int id_venta = 0;
    if (request.getParameter("id_venta") != "") {
        id_venta = Integer.parseInt(request.getParameter("id_venta"));
        
      
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Ventas ventas = VentasControlador.buscartotal(id_venta);
    if (ventas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        ventas = new Ventas();
        ventas.setId_venta(0);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_venta", ventas.getId_venta());
    obj.put("numero_factura", ventas.getNumero_factura());
    obj.put("total", ventas.getTotal());
    System.out.println("total "+ ventas.getTotal());

    out.print(obj);
    out.flush();
%>