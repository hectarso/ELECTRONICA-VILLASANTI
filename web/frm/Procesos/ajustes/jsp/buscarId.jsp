<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="controladores.AjustesControlador"%>
<%@page import="modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Ajustes ajustes = AjustesControlador.buscarId(id_ajuste_stock);
    if (ajustes !=null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {

        ajustes = new Ajustes();

        ajustes.setId_ajuste_stock(0);

        java.sql.Date fecha_ajuste_stock = new java.sql.Date(new java.util.Date().getTime());
        ajustes.setFecha_ajuste_stock(fecha_ajuste_stock);
        ajustes.setMotivo_ajuste_stock("");
    }

    String contenido_detalle = DetallesAjustesControlador.buscarIdAjuste(id_ajuste_stock);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_ajuste_stock", ajustes.getId_ajuste_stock());
    obj.put("fecha_ajuste_stock", String.valueOf(ajustes.getFecha_ajuste_stock()));
    obj.put("motivo_ajuste_stock", ajustes.getMotivo_ajuste_stock());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>