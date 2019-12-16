<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.AjustesControlador"%>
<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste_stock = Integer.parseInt(request.getParameter("id_ajuste_stock"));
    //System.out.println("EL STOCK" + id_ajuste_stock);
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);
    
   // DetallesAjustes detalleajuste = new DetallesAjustes();
   // detalleajuste.setAjuste(ajuste);

    /*ajuste = new Ajustes();
    ajuste.setId_ajuste_stock(id_ajuste_stock);*/
  //  Stocks stock = new Stocks();
  //  stock.setId_stock(id_ajuste_stock);
    //stock.setCantidad_stock(cantidad_ajuste_stock);
    //stock.setProducto(producto);
   // if (StocksControlador.sumar(stock)) {
       if (AjustesControlador.eliminar(ajuste)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
       // StocksControlador.sumar(stock);
       }
        
       /*if (DetallesAjustesControlador.eliminarc(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos eliminados.";

    }*/
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>