<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    String ruc_cliente = request.getParameter("ruc_cliente");
    String cedula = request.getParameter("ci_cliente").replaceAll("\\.", "");
    String correo_cliente = request.getParameter("correo_cliente");
    String telefono_cliente = request.getParameter("telefono_cliente");

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    cliente.setCi_cliente(cedula);
    cliente.setCorreo_cliente(correo_cliente);
    cliente.setTelefono_cliente(telefono_cliente);
    cliente.setCiudad(ciudad);

    if (ClientesControlador.modificar(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
