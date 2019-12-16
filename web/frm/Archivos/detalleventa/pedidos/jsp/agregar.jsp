<%@page import="utiles.Utiles"%>
<%@page import="controladores.PedidosControlador"%>
<%@page import="modelos.Pedidos"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente")); 

    
    String sfecha_pedido = request.getParameter("fecha_pedido");
    java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    
    
    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    pedido.setCliente(cliente);
    pedido.setFecha_pedido(fecha_pedido);
      
    
    if (PedidosControlador.agregar(pedido)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedido", String.valueOf(pedido.getId_pedido()));
    out.print(obj);
    out.flush();
    
%>