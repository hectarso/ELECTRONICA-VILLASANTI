<%@page import="modelos.FacturaCompras"%>
<%@page import="controladores.FacturaComprasControlador"%>
<%@page import="modelos.Pedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura_compra = Integer.parseInt(request.getParameter("id_factura_compra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturaCompras facturacompra = new FacturaCompras();
    facturacompra.setId_factura_compra(id_factura_compra);

    if (FacturaComprasControlador.eliminar(facturacompra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>