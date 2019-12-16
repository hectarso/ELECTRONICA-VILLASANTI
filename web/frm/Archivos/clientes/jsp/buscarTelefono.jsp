<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String telefono_cliente = request.getParameter("telefono_cliente");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setTelefono_cliente(telefono_cliente);

    ClientesControlador.buscarTelefono(cliente);
    if (cliente.getId_cliente() == 0) {
        tipo = "success";
        mensaje = "Datos de telefono repetidos";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("telefono_cliente", cliente.getTelefono_cliente());

    out.print(obj);
    out.flush();
%>
