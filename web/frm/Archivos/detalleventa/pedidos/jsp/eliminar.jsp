<%@page import="controladores.PedidosControlador"%>
<%@page import="modelos.Pedidos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);

    if (PedidosControlador.eliminar(pedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>