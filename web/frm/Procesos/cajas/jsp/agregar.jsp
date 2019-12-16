
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    String monto = request.getParameter("monto_inicial").replaceAll("\\.", "");
    int monto_inicial = Integer.parseInt(monto);
    String estado_caja = "ABIERTO";
    int id_usuario = Integer.parseInt(request.getParameter("sid_usuario"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    caja.setFecha_apertura(fecha_apertura);
    caja.setMonto_inicial(monto_inicial);
    caja.setEstado_caja(estado_caja);
    caja.setUsuario(usuario);
    
    if (CajasControlador.agregar(caja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_caja", caja.getId_caja());
    out.print(obj);
    out.flush();
%>

