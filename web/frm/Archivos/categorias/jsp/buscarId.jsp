
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);

    CategoriasControlador.buscarId(categoria);
    if (categoria.getId_categoria() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        categoria = new Categorias();
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_categoria", categoria.getId_categoria());
    obj.put("nombre_categoria", categoria.getNombre_categoria());

    out.print(obj);
    out.flush();
%>