
<%@page import="modelos.Establecimientos"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);

    TimbradosControlador.buscarId(timbrado);
    if (timbrado.getId_timbrado() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
     
        timbrado.setId_timbrado(0);
        timbrado.setNumero_timbrado("");
        timbrado.setFechaini_timbrado(null);
        timbrado.setFechaact_timbrado(null);
        timbrado.setFechafin_timbrado(null);
        timbrado.setDesde_timbrado(0);
        timbrado.setHasta_timbrado(0);
        timbrado.setEstado_timbrado("");
        
        Establecimientos establecimiento = new Establecimientos();
        establecimiento.setId_establecimiento(0);
        establecimiento.setNombre_establecimiento("");
        timbrado.setEstablecimiento(establecimiento);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_timbrado", timbrado.getId_timbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    obj.put("fechaini_timbrado", String.valueOf(timbrado.getFechaini_timbrado()));
    obj.put("fechaact_timbrado", String.valueOf(timbrado.getFechaact_timbrado()));
    obj.put("fechafin_timbrado", String.valueOf(timbrado.getFechafin_timbrado()));
    obj.put("desde_timbrado", timbrado.getDesde_timbrado());
    obj.put("hasta_timbrado", timbrado.getHasta_timbrado());
    obj.put("estado_timbrado", timbrado.getEstado_timbrado());
    obj.put("id_establecimiento", timbrado.getEstablecimiento().getId_establecimiento());
    obj.put("nombre_establecimiento", /*String.valueOf(*/timbrado.getEstablecimiento().getNombre_establecimiento());

    out.print(obj);
    out.flush();
%>