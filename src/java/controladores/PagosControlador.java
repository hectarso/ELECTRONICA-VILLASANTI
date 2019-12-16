package controladores;

import modelos.Pagos;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.Utiles;

public class PagosControlador {

    public static boolean agregar(Pagos pago) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pagos(forma_pago) " + "values('" + pago.getForma_pago() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Pagos buscarId(Pagos pago) {

        if (Conexion.conectar()) {
            String sql = "select * from pagos where id_pago ='" + pago.getId_pago() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    pago.setId_pago(rs.getInt("id_pago"));
                    pago.setForma_pago(rs.getString("forma_pago"));
                } else {
                    pago.setId_pago(0);
                    pago.setForma_pago("");
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return pago;
    }

    public static String buscarNombre(String forma, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(forma);
                String sql = "select * from pagos where upper(forma_pago) like '%"
                        + forma.toUpperCase() + "%'"
                        + "order by id_pago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                + "<td>" + rs.getString("forma_pago") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Pagos pago) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pagos set forma_pago= '" + pago.getForma_pago() + "', ruc_pago= '" + " where id_pago=" + pago.getId_pago();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Pagos pago) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pagos where id_pago=" + pago.getId_pago();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    //Aqui la función buscarPago buscara los nombres para ver si no se repiten
    public static Pagos buscarPago(Pagos pago) {

        if (Conexion.conectar()) {
            String sql = "select * from pagos where forma_pago ='" + pago.getForma_pago() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    pago.setId_pago(0);

                } else {

                    pago.setId_pago(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return pago;
    }

    
}
