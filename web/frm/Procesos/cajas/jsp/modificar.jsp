<%@page import="utiles.Utiles"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    //int monto_inicial = Integer.parseInt(request.getParameter("monto_inicial"));
    //String sfecha_apertura = request.getParameter("fecha_apertura");
    //java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    //caja.setFecha_apertura(fecha_apertura);
    //caja.setMonto_inicial(monto_inicial);
    //caja.setEstado_caja(estado_caja);
    //caja.setNombre_caja(nombre_caja);
    
    if (CajasControlador.modificarestado(caja)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
