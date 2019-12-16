<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    String nombre_categoria = request.getParameter("nombre_categoria");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    categoria.setNombre_categoria(nombre_categoria);

    if (CategoriasControlador.modificar(categoria)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
