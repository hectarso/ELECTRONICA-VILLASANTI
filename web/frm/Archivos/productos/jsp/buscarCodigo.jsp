<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String codigo_producto = request.getParameter("codigo_producto");
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setCodigo_producto(codigo_producto);

    ProductosControlador.buscarCodigo(producto);
    if (producto.getId_producto() == 0) {
        tipo = "success";
        mensaje = "Codigo de producto repetido";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("codigo_producto", producto.getCodigo_producto());

    out.print(obj);
    out.flush();
%>