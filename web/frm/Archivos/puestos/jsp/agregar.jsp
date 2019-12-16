<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    String nombre_puesto = request.getParameter("nombre_puesto");

    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);

    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    puesto.setNombre_puesto(nombre_puesto);

    puesto.setEstablecimiento(establecimiento);

    if (PuestosControlador.agregar(puesto)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>