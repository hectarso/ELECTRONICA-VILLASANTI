
<%@page import="controladores.MarcasControlador"%>
<%@page import="modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);

    MarcasControlador.buscarId(marca);
    if (marca.getId_marca() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_marca", marca.getId_marca());
    obj.put("nombre_marca", marca.getNombre_marca());

    out.print(obj);
    out.flush();
%>