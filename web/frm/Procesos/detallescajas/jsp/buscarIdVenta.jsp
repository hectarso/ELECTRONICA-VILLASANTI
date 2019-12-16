<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="modelos.Clientes"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_venta = Integer.parseInt(request.getParameter("id_venta
    int id_venta = 0;
    if (request.getParameter("id_venta") != "") {
        id_venta = Integer.parseInt(request.getParameter("id_venta"));
        
      
    }
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";


    Ventas venta = VentasControlador.buscartotal(id_venta);
    if (venta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        venta = new Ventas();
        venta.setId_venta(0);
        //venta.setTotal(0);
    }
    
    //String contenido_detalle = VentasControlador.buscarIdVenta(id_venta);
    //VentasControlador.buscarId(id_venta);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    //obj.put("id_venta", id_venta);
    //System.out.println("idfactura" + id_venta);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_venta", venta.getId_venta());
   // System.out.println("idfactura==" + venta.getId_venta());
    obj.put("numero_factura", venta.getNumero_factura());
    obj.put("total", venta.getTotal());
    System.out.println("total " + venta.getTotal());
    
    out.print(obj);
    out.flush();
%>