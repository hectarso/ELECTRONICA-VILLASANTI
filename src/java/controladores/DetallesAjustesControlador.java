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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ajustes;
import modelos.DetallesAjustes;
import modelos.Productos;
import utiles.Conexion;
import utiles.Utiles;

public class DetallesAjustesControlador {

    public static DetallesAjustes buscarId(int id){
        DetallesAjustes detalleajuste = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajuste_stock_detalle d,"
                        + " ajustes_stocks a, productos p "
                        + "where d.id_ajuste_stock=a.id_ajuste_stock"
                        + " and d.id_producto=p.id_producto "
                        + "and d.id_ajuste_stock_detalle=?";
                System.out.println("sql"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleajuste = new DetallesAjustes();
                        detalleajuste.setId_ajuste_stock_detalle(rs.getInt("id_ajuste_stock_detalle"));
                        detalleajuste.setCantidad_ajuste_stock(rs.getInt("cantidad_ajuste_stock"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setPrecio_compra_producto(rs.getInt("precio_compra_producto"));
                        detalleajuste.setProducto(producto);

                        Ajustes ajuste = new Ajustes();
                        ajuste.setId_ajuste_stock(rs.getInt("id_ajuste_stock"));
                        detalleajuste.setAjuste(ajuste);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleajuste;
    }

    public static String buscarIdAjuste(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ajuste_stock_detalle  dp "
                        + "left join ajustes_stocks p on p.id_ajuste_stock=dp.id_ajuste_stock "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_ajuste_stock=" + id + " "
                        + "order by id_ajuste_stock_detalle";
                System.out.println("hola " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_ajuste_stock");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_ajuste_stock_detalle") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_ajuste_stock_detalle") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button> <button onclick='p(" + rs.getString("id_ajuste_stock_detalle") + ")''  type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-trash'> </span></button> </td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                //System.out.println("--> " + ex.getLocalizedMessage());
                Logger.getLogger(DetallesAjustesControlador.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "select * from ajuste_stock_detalle dp "
                        + "left join ajustes_stocks p on p.id_ajuste_stock=dp.id_ajuste_stock "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "left join stock s on s.id_producto=p.id_producto "
                        + "where upper(a.nombre_producto) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ajuste_stock_detalle "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_ajuste_stock_detalle") + "</td>"
                                + "<td>" + rs.getInt("id_ajuste_stock") + "</td>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombreproducto") + "</td>"
                                + "<td>" + rs.getInt("preciocompra_producto") + "</td>"
                                //+ "<td>" + rs.getInt("iva_producto") + "</td>"
                                // + "<td>" + rs.getInt("cantidad_exi") + "</td>"
                                + "<td>" + rs.getInt("cantidad_ajuste_stock") + "</td>"
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

    public static boolean agregar(DetallesAjustes detalleajuste) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into ajuste_stock_detalle (id_ajuste_stock,"
                    + " id_producto, cantidad_ajuste_stock) "
                    + "values (?,?,?)";

            /*String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste) "
                    + "values (?,?,?)";*/
            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste_stock());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste_stock());

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

    public static boolean modificar(DetallesAjustes detalleajuste) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ajuste_stock_detalle set "
                    + "id_ajuste_stock=?,"
                    + "id_producto=?,"
                    + "cantidad_ajuste_stock=? "
                    + "where id_ajuste_stock_detalle=?";

            /*String sql = "update detallesajustes set "
                    + "id_ajuste=?,"
                    + "id_producto=?,"
                    + "cantidad_ajuste=? "
                    + "where id_detalleajuste=?";*/
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste_stock());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste_stock());

                ps.setInt(4, detalleajuste.getId_ajuste_stock_detalle());
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

    public static boolean eliminar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where id_ajuste_stock_detalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getId_ajuste_stock_detalle());
                System.out.println("detallesqlstock+ " + ps);
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

    public static boolean eliminarDetalle(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where id_ajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste_stock());
                System.out.println("detallesqlstock+ " + ps);
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

    public static boolean eliminarProductoAjuste(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ajuste_stock_detalle where id_ajuste_stock=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getId_ajuste_stock());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + ajuste.getId_ajuste_stock());
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

    public static boolean buscarDuplicadosD(DetallesAjustes ajustedetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from ajuste_stock_detalle where id_ajuste_stock = " 
                    + ajustedetalle.getAjuste().getId_ajuste_stock()
                    + " and id_producto = "
                    + ajustedetalle.getProducto().getId_producto() + "";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    ajustedetalle.setId_ajuste_stock_detalle(0);

                } else {
                    String sql1 = "insert into ajuste_stock_detalle (id_ajuste_stock,"
                            + " id_producto, cantidad_ajuste_stock) "
                            + "values (?,?,?)";

                    /*String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste) "
                    + "values (?,?,?)";*/
                    System.out.println(sql1);
                    try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql1)) {
                        ps1.setInt(1, ajustedetalle.getAjuste().getId_ajuste_stock());
                        ps1.setInt(2, ajustedetalle.getProducto().getId_producto());
                        ps1.setInt(3, ajustedetalle.getCantidad_ajuste_stock());

                        ps1.executeUpdate();
                        ps1.close();
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

                    //return null;
                    //return ciudad;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
}
