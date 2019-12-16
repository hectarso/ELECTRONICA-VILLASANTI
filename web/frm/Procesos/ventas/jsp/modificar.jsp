<%@page import="modelos.Puestos"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Timbrados"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
    //int nro_factura_venta = Integer.parseInt(request.getParameter("nro_factura_venta"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    venta.setFecha_venta(fecha_venta);
    //venta.setNro_factura_venta(nro_factura_venta);
    venta.setTimbrado(timbrado);
    venta.setCliente(cliente);
    venta.setPuesto(puesto);
    venta.setEstablecimiento(establecimiento);

    if (VentasControlador.modificar(venta)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
