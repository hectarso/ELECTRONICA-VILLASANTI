package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Cajas;
import modelos.DetallesCajas;
import modelos.Pagos;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;

//import javawebts.modelos.Ventas;
//import javawebts.modelos.DetallesCajas;
//import javawebts.modelos.Cajas;
//import javawebts.utiles.Conexion;
//import javawebts.utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join ventas v on v.id_venta=dc.id_venta "
                        + "left join pagos pa on pa.id_pago=dc.id_pago "
                        + "where dc.id_detallecaja=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecaja--> " + sql+id);
                    if (rs.next()) {
                        
                        detallecaja = new DetallesCajas();
                        detallecaja.setId_detallecaja(rs.getInt("id_detallecaja"));
                        detallecaja.setImporte_caja(rs.getInt("importe_caja"));
                        //detallecaja.setVuelto(rs.getInt("vuelto"));

                        Ventas venta = new Ventas();
                        venta.setId_venta(rs.getInt("id_venta"));
                        venta.setNumero_factura(rs.getInt("numero_factura"));
                        detallecaja.setVenta(venta);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);

                        Pagos pago = new Pagos();
                        pago.setId_pago(rs.getInt("id_pago"));
                        //pago.setForma_pago(rs.getString("forma_pago"));
                        detallecaja.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join ventas v on v.id_venta=dc.id_venta "
                        + "left join pagos p on p.id_pago=dc.id_pago "
                        + "where dc.id_caja=" + id + " "
                        + "order by id_detallecaja";
                System.out.println("dca--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe_caja");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td class='centrado'>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td class='centrado'>" + rs.getString("id_venta") + "</td>"
                                //+ "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td class='centrado'>" + rs.getString("id_pago") + "</td>"
                                + "<td class='centrado'>" + rs.getString("forma_pago") + "</td>"
                               // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join ventas v on v.id_venta=dc.id_venta "
                        + "left join pagos pa on pa.id_pago=dc.id_pago "
                        + "where upper(v.nombre_venta) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_venta") + "</td>"
                                + "<td>" + rs.getInt("importe_caja") + "</td>"
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

    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescajas "
                    + "(id_venta,id_pago,id_caja,importe_caja) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, detallecaja.getVenta().getId_venta());
                ps.setInt(2, detallecaja.getPago().getId_pago());
                ps.setInt(3, detallecaja.getCaja().getId_caja());
                ps.setInt(4, detallecaja.getImporte_caja());
                //ps.setInt(5, detallecaja.getVuelto());

                ps.executeUpdate();
                System.out.println("agregarcajadetalle "+sql);
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

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "id_caja=?,"
                    + "id_venta=?,"
                    + "cantidad_ventacaja=? "
                    + "where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getVenta().getId_venta());
                //  ps.setInt(3, detallecaja.getCantidad_ventacaja());
                ps.setInt(4, detallecaja.getId_detallecaja());
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

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_detallecaja());
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

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join ventas v on v.id_venta=dc.id_factura_venta "
                        + " where c.id_caja= " + detallecaja.getCaja().getId_caja();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_stock= cantidad_stock - " + rs.getInt("cantidad_ventacaja") + " where id_venta=" + rs.getInt("id_venta") + "";

                        System.out.println(" descontar stock" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarVentaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + caja.getId_caja());
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
