<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Puestos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    String nombre_puesto = request.getParameter("nombre_puesto");
    //String actividad_economica = request.getParameter("actividad_economica");
    //String ruc = request.getParameter("ruc");
    //String representante = request.getParameter("representante");
    //String direccion= request.getParameter("direccion");
    //String telefono = request.getParameter("telefono");
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));

    String tipo = "error";
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    puesto.setNombre_puesto(nombre_puesto);

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    puesto.setEstablecimiento(establecimiento);

    String mensaje = "Datos no modificados";
    if (PuestosControlador.modificar(puesto)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
