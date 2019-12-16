
<%@page import="java.sql.Date"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="utiles.Conexion"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>
<%
    String dirPath="/rpt";
    String realPath=this.getServletContext().getRealPath(dirPath);
    String listado=request.getParameter("l");
    Date desde_id=Date.valueOf(request.getParameter("d").toString());
    Date hasta_id=Date.valueOf(request.getParameter("h").toString());
    String jasperReport=listado+".jasper";
    JasperPrint print=null;
    Connection conn=null;
    
    //Clientes clienteLogueado=(Clientes) sesion.getAttribute("clienteLoagueado");
    
    try{
        Conexion.conectar();
        conn=Conexion.getConn();
        Map parameters=new HashMap();
        parameters.put("desde_id",desde_id);
        parameters.put("hasta_id",hasta_id);
        //parameters.put("USUARIO",clienteLogueado.getCliente_cliente());
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>
