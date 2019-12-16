/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Establecimientos;
import modelos.Puestos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class PuestosControlador {

    public static boolean agregar(Puestos puesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into puestos(nombre_puesto"
                    + ",id_establecimiento) "
                    + "values('" + puesto.getNombre_puesto() + "','"
                    + puesto.getEstablecimiento().getId_establecimiento() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Puestos buscarId(Puestos puesto) {
        if (Conexion.conectar()) {
            String sql = "select * from puestos p, establecimientos e "
                    + " where p.id_establecimiento=e.id_establecimiento and "
                    + "id_puesto='" + puesto.getId_puesto() + "'";

            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    puesto.setId_puesto(rs.getInt("id_puesto"));
                    puesto.setNombre_puesto(rs.getString("nombre_puesto"));
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));

                    puesto.setEstablecimiento(establecimiento);

                } else {
                    puesto.setId_puesto(0);
                    puesto.setNombre_puesto("");
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");

                    puesto.setEstablecimiento(establecimiento);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return puesto;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from puestos p left join establecimientos e on p.id_establecimiento=e.id_establecimiento where upper(nombre_puesto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_puesto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_puesto") + "</td>"
                                + "<td>" + rs.getString("nombre_puesto") + "</td>"
                                + "<td>" + rs.getString("id_establecimiento") + "</td>"
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

    public static boolean modificar(Puestos puesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update puestos set nombre_puesto='" + puesto.getNombre_puesto()
                    + "',id_establecimiento='" + puesto.getEstablecimiento().getId_establecimiento() + ""
                    + "' where id_puesto=" + puesto.getId_puesto();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Puestos puesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from puestos where id_puesto=" + puesto.getId_puesto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

}
