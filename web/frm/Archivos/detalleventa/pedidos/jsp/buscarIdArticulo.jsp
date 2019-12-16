
<%@page import="controladores.ArticulosControlador"%>
<%@page import="modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_articulo =Integer.parseInt(request.getParameter("id_articulo"));
    String nombre_articulo =request.getParameter("nombre_articulo");
    String codigo_articulo =request.getParameter("codigo_articulo");
    
    Articulos articulo= new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setNombre_articulo(nombre_articulo);
    articulo.setCodigo_articulo(codigo_articulo);
    
    String mensaje= "datos no encontrados";
    articulo= ArticulosControlador.buscarId(articulo);
    System.out.println("llega");
    if (articulo.getId_articulo()!=0){
        mensaje ="datos encontrados";
    }
    String tipo="error";
    String nuevo="true";
    if (articulo!=null){
        tipo= "success";
        mensaje= "datos encontrados.";
        nuevo= "false";
    }
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_articulo",articulo.getId_articulo());
    obj.put("nombre_articulo",articulo.getNombre_articulo());
    obj.put("codigo_articulo",articulo.getCodigo_articulo());
    //obj.put("id_articulo",articulo.getArticulo(),getId_articulo());
    //obj.put("nombre_articulo",articulo.getArticulo().getNombre_articulo());
    out.print(obj);
    out.flush();
    %>