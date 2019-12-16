    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Productos;
import modelos.DetallesVentas;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
//import modelos.Ivas;

public class DetallesVentasControlador {

    public static DetallesVentas buscarId(int id) {
        DetallesVentas detalleventa = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        //+"left join ivas i on i.id_iva=p.id_iva "
                        + "where dv.id_detalle_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleventa = new DetallesVentas();
                        detalleventa.setId_detalle_venta(rs.getInt("id_detalle_venta"));
                        detalleventa.setCantidad_producto_venta(rs.getInt("cantidad_producto_venta"));
                        detalleventa.setPreciototal_venta(rs.getInt("preciototal_venta"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        producto.setPrecio_venta_producto(rs.getInt("precio_venta_producto"));
                        producto.setIva_producto(rs.getInt("iva_producto"));

                        detalleventa.setProducto(producto);

                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        detalleventa.setVenta(venta);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleventa;
    }

    public static String buscarIdVenta(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta="+id+"";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    BigDecimal stotal = BigDecimal.ZERO;
                    //BigDecimal iva = BigDecimal.ZERO;
                    int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_venta"));
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    int totalt = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad1 = rs.getInt("cantidad_producto_venta");
                        int venta = rs.getInt("precio_venta_producto");
                        if (iva == 0) {

                            totalexentas = venta * cantidad1;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad1;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad1;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 /21;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;
                        
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_producto_venta");
                        
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        BigDecimal subtotal = rs.getBigDecimal("precio_venta_producto");
                        total1 = total1.add(subtotal);
                        //para el subtotal
                        BigDecimal subtotalp = subtotal.multiply(cantidad);
                        stotal = stotal.add(subtotalp);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                        //iva = iva.add(subiva);
                        //subtotaliva
                        

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalle_venta") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("precio_venta_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(subtotalp) + "</td>"  
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalle_venta") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=5>TOTAL</td><td class='centrado'>" + df.format(total) +"</td></tr>";
                        tabla += "<tr><td colspan=5>"
                                + "<b>TOTALES:</b>"
                                + "</td><td class='centrado'><b>" + df.format(total) + "</b></td>"
                                //+ "<td><b>" + df.format(total5) + "</b></td>"
                                + "<th class='centrado'>" + df.format(total10) + "</td></tr>";
                        
                        /*tabla += "<tr><td colspan=6><b>LIQUIDACION DEL IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";
                        tabla += "<tr><td colspan=7 ><b><P ALIGN=right>TOTAL A PAGAR:</b></td><td class='left'><b>" + df.format(totalf) + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";*/
                        tabla += "<tr><td colspan=9><b>Liquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(totales) + " </td> </tr>";

                        //tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                      tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total a Pagar: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalt) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                        
                        //String sql2 = "select sum(" + cant + " * precio_venta_producto) + " + total + " as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta="+ id + "";
                        //System.out.println("total " + sql2);
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

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where upper(p.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalle_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalle_venta") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                + "<td>" + rs.getString("precio_venta_producto") + "</td>"
                                + "<td>" + rs.getString("iva_producto") + "</td>"
                                //+ "<td>" + rs.getString("porcentaje_iva") + "</td>"
                                //+ "<td>" + rs.getString("nombre_iva") + "</td>"
                                + "<td>" + rs.getInt("cantidad_producto_venta") + "</td>"
                                + "<td>" + rs.getInt("preciototal_venta") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
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

    public static boolean agregar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesventas "
                    + "(id_venta, id_producto, cantidad_producto_venta, preciototal_venta) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVenta().getId_venta());
                ps.setInt(2, detalleventa.getProducto().getId_producto());
                ps.setInt(3, detalleventa.getCantidad_producto_venta());
                ps.setInt(4, detalleventa.getPreciototal_venta());
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

    public static boolean modificar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesventas set "
                    + "id_venta=?,"
                    + "id_producto=?,"
                    + "cantidad_producto_venta=?,"
                    + "preciototal_venta=? "
                    + "where id_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getVenta().getId_venta());
                ps.setInt(2, detalleventa.getProducto().getId_producto());
                ps.setInt(3, detalleventa.getCantidad_producto_venta());
                ps.setInt(4, detalleventa.getPreciototal_venta());
                ps.setInt(5, detalleventa.getId_detalle_venta());
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

    public static boolean eliminar(DetallesVentas detalleventa) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_detalle_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleventa.getId_detalle_venta());
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

    public static boolean eliminarProductoVenta(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + venta.getId_venta());
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
