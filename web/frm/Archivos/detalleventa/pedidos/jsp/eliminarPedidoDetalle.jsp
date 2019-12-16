<%@page import="controladores.DetallesPedidosControlador"%>
<%@page import="modelos.DetallesPedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesPedidos detallepedido = new DetallesPedidos();
    detallepedido.setId_detallepedido(id_detallepedido);

    if (DetallesPedidosControlador.eliminar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>