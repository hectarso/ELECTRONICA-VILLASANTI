<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_cliente =Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente =request.getParameter("nombre_cliente");
    String apellido_cliente =request.getParameter("apellido_cliente");
    Clientes cliente= new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    String mensaje= "datos no encontrados";
    cliente= ClientesControlador.buscarId(cliente);
    System.out.println("llega");
    if (cliente.getId_cliente()!=0){
        mensaje ="datos encontrados";
    }
    String tipo="error";
    String nuevo="true";
    if (cliente!=null){
        tipo= "success";
        mensaje= "datos encontrados.";
        nuevo= "false";
    }
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_cliente",cliente.getId_cliente());
    obj.put("nombre_cliente",cliente.getNombre_cliente());
    obj.put("apellido_cliente",cliente.getApellido_cliente());
    //obj.put("id_cliente",cliente.getCliente(),getId_cliente());
    //obj.put("nombre_cliente",cliente.getCliente().getNombre_cliente());
    out.print(obj);
    out.flush();
    %>