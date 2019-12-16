
<%@page import="modelos.Clientes"%>
<%@page import="modelos.Tipo_facturas"%>
<%@page import="controladores.Tipo_facturasControlador"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipo_factura = 0;
    
   // int id_cuenta = Integer.parseInt(request.getParameter("id_cuenta"));
   // int nro_cuota = Integer.parseInt(request.getParameter("nro_cuota"));
    String stotal = request.getParameter("total");
   // String montoenletras = Utiles.cantidadConLetra(stotal);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
  // CuentasClientes cuentas = new CuentasClientes();
   // cuentas.setId_cuenta(id_cuenta);
   
    Tipo_facturas tipofactura = new Tipo_facturas();
    tipofactura.setId_tipo_factura(0);
   // recibo.setCuentas(cuentas);
   // recibo.setMontoenletras(montoenletras);
    
    
    if (Tipo_facturasControlador.agregar(tipofactura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //obj.put("id", recibo.getId_recibo());
    out.print(obj);
    out.flush();
%>

