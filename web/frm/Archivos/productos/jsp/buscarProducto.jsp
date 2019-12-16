<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_producto = request.getParameter("nombre_producto");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setNombre_producto(nombre_producto);

    ProductosControlador.buscarProducto(producto);
    if (producto.getId_producto() == 0) {
        tipo = "success";
        mensaje = "Datos de Productos repetidos";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_producto", producto.getNombre_producto());

    out.print(obj);
    out.flush();
%>