/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;




import modelos.FacturaDetalleCompras;
import modelos.FacturaCompras;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;
//import modelos.Tipo_facturas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Productos;

public class FacturaDetalleComprasControlador {
    
    public static FacturaDetalleCompras buscarId(int id) {
        FacturaDetalleCompras facturadetallecompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_compras fdc "+
                             "left join factura_compras fc on fc.id_factura_compra=fdc.id_factura_compra "+
                             "left join productos a on a.id_producto=fdc.id_producto "+
                             "where fdc.id_factura_detalle_compra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturadetallecompra = new FacturaDetalleCompras();
                        facturadetallecompra.setId_factura_detalle_compra(rs.getInt("id_factura_detalle_compra"));
                        facturadetallecompra.setCantidad_compra(rs.getInt("cantidad_compra"));
                        //facturadetallecompra.setSubtotal_compra(rs.getInt("subtotal_compra"));
                        
                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setPrecio_compra_producto(rs.getInt("precio_compra_producto"));
                        facturadetallecompra.setProducto(producto);
                        
                        FacturaCompras facturacompra = new FacturaCompras();
                        facturacompra.setId_factura_compra(rs.getInt("id_factura_compra"));
                        facturadetallecompra.setFacturacompra(facturacompra);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturadetallecompra;
    }
    
    public static String buscarIdFacturaCompra(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_compras fdc "+
                        "left join factura_compras fc on fc.id_factura_compra=fdc.id_factura_compra "+
                        "left join productos a on a.id_producto=fdc.id_producto "+
                        "where fdc.id_factura_compra="+id+" "+
                        "order by id_factura_detalle_compra";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###" );
                    String tabla = "";
                    int total = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    BigDecimal totalt = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_compra");
                        int venta = rs.getInt("precio_compra_producto");

                        if (iva == 0) {

                            totalexentas = venta * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        total = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        
                        BigDecimal cantidad1 = rs.getBigDecimal("cantidad_compra");
                        BigDecimal precio = rs.getBigDecimal("precio_compra_producto");
                        BigDecimal total_compra = precio.multiply(cantidad1);
                        totalt = totalt.add(cantidad1);
                        total1 = total1.add(total_compra);
                        System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_compra") + "</td>"
                               + "<td>" + rs.getString("id_producto") + "</td>"
                               + "<td>" + rs.getString("nombre_producto") + "</td>" 
                               + "<td class='centrado'>" + df.format(precio) + "</td>"
                                //+ "<td class='centrado'>" + rs.getString("precio_compra_producto") + "</td>"
                               + "<td class='centrado'>" + rs.getInt("cantidad_compra") + "</td>"
                               //+ "<td class='centrado'>" + df.format(total_compra) + "</td>"
                                //+ "<td>" + df.format(totalexentas) + "</td>"
                                //+ "<td>" + df.format(totaliva5) + "</td>"
                                + "<td class='centrado'>" + df.format(totaliva10) + "</td>"
                                
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_factura_detalle_compra")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=4>TOTAL</td><td class='centrado'>"+totalt+
                                //"<td class='centrado'>"+df.format(total1)+"</td>"
                                //+ "<td class='centrado'>" + df.format(totale) + "</td>"
                                //+ "<td class='centrado'>" + df.format(total5) + "</td>"
                                 "<td class='centrado'>" + df.format(total10) + "</td>";
                        
                        
                        tabla += "<tr><td colspan=9><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";

                        tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                        
                        String sql2 = "select sum(cantidad_compra * precio_compra_producto) + " + total + " as TotalaPagar from factura_detalle_compras fdc left join factura_compras fc on fc.id_factura_compra=fdc.id_factura_compra left join productos a on a.id_producto=fdc.id_producto where fdc.id_factura_compra="+ id + "";

                        try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                            ResultSet rs1 = ps1.executeQuery();
                            if (rs1.next()) {
                                int total_pagar = rs1.getInt("TotalaPagar");
                                
                                tabla += "<tr ><td colspan=9  ><b><P ALIGN=left>Total a Pagar en Gs: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(total_pagar) + ".----" + "</td></tr>";
                            }
                            System.out.println("Total a Pagar "+ sql2);
                            System.out.println(rs1.getString("TotalaPagar"));
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());

                        }
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_detalle_compras fdc "+
                        "left join factura_compras fc on fv.id_factura_compra=fdc.id_factura_compra "+
                        "left join productos a on a.id_producto=fdc.id_producto "+
                        "where upper(a.nombre_producto) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_factura_detalle_compra "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_factura_detalle_compra") + "</td>"
                               + "<td>" + rs.getString("id_factura_compra") + "</td>"
                               + "<td>" + rs.getString("id_producto") + "</td>"
                               + "<td>" + rs.getString("nombre_producto") + "</td>"
                               + "<td>" + rs.getInt("cantidad_compra") + "</td>" 
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into factura_detalle_compras "
                    + "(id_factura_compra,id_producto,cantidad_compra) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getFacturacompra().getId_factura_compra());
                ps.setInt(2, facturadetallecompra.getProducto().getId_producto());
                ps.setInt(3, facturadetallecompra.getCantidad_compra());
                //ps.setInt(4, facturadetallecompra.getSubtotal_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_detalle_compras set "
                    + "id_factura_compra=?,"
                    + "id_producto=?,"
                    + "cantidad_compra=? "
                    //+ "subtotal_compra=? "
                    + "where id_factura_detalle_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getFacturacompra().getId_factura_compra());
                ps.setInt(2, facturadetallecompra.getProducto().getId_producto());
                ps.setInt(3, facturadetallecompra.getCantidad_compra());
                //ps.setInt(4, facturadetallecompra.getSubtotal_compra());
                ps.setInt(4,facturadetallecompra.getId_factura_detalle_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(FacturaDetalleCompras facturadetallecompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_compras where id_factura_detalle_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturadetallecompra.getId_factura_detalle_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminarArticuloPedido(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_detalle_compras where id_factura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturacompra.getId_factura_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + facturacompra.getId_factura_compra());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
}
