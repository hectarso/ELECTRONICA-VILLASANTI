/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Proveedores;
//import modelos.Tipo_facturas;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.FacturaCompras;
import modelos.Tipo_facturas;



public class FacturaComprasControlador {

    public static FacturaCompras buscarId(int id) {
        FacturaCompras facturacompra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_compras fc "
                        + "left join proveedores p on fc.id_proveedor=p.id_proveedor "
                        + "left join tipo_facturas tf on fc.id_tipo_factura=tf.id_tipo_factura "
                        + "where id_factura_compra=?";
                System.out.println("sql ::"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        facturacompra = new FacturaCompras();
                        facturacompra.setId_factura_compra(rs.getInt("id_factura_compra"));
                        Proveedores proveedor = new Proveedores();
                        proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                        proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                        proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                        facturacompra.setProveedor(proveedor);
                        facturacompra.setFecha_factura_compra(rs.getDate("fecha_factura_compra"));
                        Tipo_facturas tipofactura = new Tipo_facturas();
                        tipofactura.setId_tipo_factura(rs.getInt("id_tipo_factura"));
                        tipofactura.setNombre_tipo_factura(rs.getString("nombre_tipo_factura"));
                        facturacompra.setTipofactura(tipofactura);
                        
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturacompra;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from factura_compras fc "
                        + "left join proveedores p on p.id_proveedor=fc.id_proveedor "
                        + "where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_factura_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura_compra") + "</td>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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

    public static boolean agregar(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = facturacompra.getProveedor().getId_proveedor();
            Date v2 = facturacompra.getFecha_factura_compra();
            int v3 = facturacompra.getTipofactura().getId_tipo_factura();
            String sql = "insert into factura_compras(id_proveedor, fecha_factura_compra, id_tipo_factura) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura_compra = keyset.getInt(1);
                    facturacompra.setId_factura_compra(id_factura_compra);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(FacturaCompras facturacompra) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update factura_compras set id_proveedor=?,"
                    + "id_tipo_factura=? "
                    + "where id_factura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, facturacompra.getProveedor().getId_proveedor());
                ps.setInt(2, facturacompra.getTipofactura().getId_tipo_factura());
                ps.setInt(3, facturacompra.getId_factura_compra());
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

    public static boolean eliminar(FacturaCompras facturacompra  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from factura_compras where id_factura_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, facturacompra.getId_factura_compra());
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
}