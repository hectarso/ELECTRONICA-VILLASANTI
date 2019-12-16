<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.PuestosControlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);

    if (PuestosControlador.eliminar(puesto)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
