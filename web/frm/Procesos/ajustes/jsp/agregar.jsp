<%@page import="controladores.AjustesControlador"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));
    int id_usuario = Integer.parseInt(request.getParameter("sid_usuario"));
    String sfecha_ajuste_stock = request.getParameter("fecha_ajuste_stock");
    java.sql.Date fecha_ajuste_stock = Utiles.stringToSqlDate(sfecha_ajuste_stock);
      String motivo_ajuste_stock = request.getParameter("motivo_ajuste_stock");
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);
    ajuste.setFecha_ajuste_stock(fecha_ajuste_stock);
    ajuste.setMotivo_ajuste_stock(motivo_ajuste_stock);
    ajuste.setUsuario(usuario);
    if (AjustesControlador.agregar(ajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_ajuste_stock", String.valueOf(ajuste.getId_ajuste_stock()));
    out.print(obj);
    out.flush();

%>