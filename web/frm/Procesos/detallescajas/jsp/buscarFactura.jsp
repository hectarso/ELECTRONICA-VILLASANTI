<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Ventas venta = new Ventas();
    venta.setNumero_factura(numero_factura);
//aqui modifique decia VentasControlador.buscarFactura(venta)
    VentasControlador.buscarId(numero_factura);
    if (venta.getId_venta() == 0) {
        tipo = "success";
        mensaje = "Numero de factura repetido";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("numero_factura", venta.getNumero_factura());

    out.print(obj);
    out.flush();
%>