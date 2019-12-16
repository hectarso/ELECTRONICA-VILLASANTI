<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    String forma_pago = request.getParameter("forma_pago");
    
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);     
    String mensaje = "datos no encontrados";
    
    pago = PagosControlador.buscarId(pago);
    System.out.println("llega");
    if (pago.getId_pago() != 0) {
        mensaje = "datos encontrados";
    }
    String tipo = "error";
    String nuevo = "true";
    if (pago != null) {
        tipo = "success";
        mensaje = "datos encontrados.";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_pago", pago.getId_pago());
    obj.put("forma_pago", pago.getForma_pago());
    out.print(obj);
    out.flush();
%>