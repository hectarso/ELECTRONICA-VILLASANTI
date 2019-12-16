
<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    String forma_pago = request.getParameter("forma_pago");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);
    
    if (PagosControlador.modificar(pago)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>