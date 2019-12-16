<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String telefono_establecimiento = request.getParameter("telefono_establecimiento");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setTelefono_establecimiento(telefono_establecimiento);

    EstablecimientosControlador.buscarTelefono(establecimiento);
    if (establecimiento.getId_establecimiento() == 0) {
        tipo = "success";
        mensaje = "Datos de telefono repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("telefono_establecimiento", establecimiento.getTelefono_establecimiento());

    out.print(obj);
    out.flush();
%>