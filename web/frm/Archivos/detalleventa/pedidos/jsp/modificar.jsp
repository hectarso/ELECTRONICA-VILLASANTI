<%@page import="controladores.PedidosControlador"%>
<%@page import="modelos.Pedidos"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);

    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    pedido.setCliente(cliente);
   
    if (PedidosControlador.modificar(pedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>