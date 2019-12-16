<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    String ruc_establecimiento = request.getParameter("ruc_establecimiento");
    String direccion_establecimiento = request.getParameter("direccion_establecimiento");
    String telefono_establecimiento = request.getParameter("telefono_establecimiento");
    String representante_establecimiento = request.getParameter("representante_establecimiento");

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    establecimiento.setNombre_establecimiento(nombre_establecimiento);
    establecimiento.setRuc_establecimiento(ruc_establecimiento);
    establecimiento.setDireccion_establecimiento(direccion_establecimiento);
    establecimiento.setTelefono_establecimiento(telefono_establecimiento);
    establecimiento.setRepresentante_establecimiento(representante_establecimiento);
    establecimiento.setCiudad(ciudad);

    if (EstablecimientosControlador.modificar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
