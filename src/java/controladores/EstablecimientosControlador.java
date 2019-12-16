package controladores;

import modelos.Establecimientos;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;
import utiles.Utiles;

public class EstablecimientosControlador {

    public static boolean agregar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into establecimientos(nombre_establecimiento, ruc_establecimiento, direccion_establecimiento , id_ciudad, telefono_establecimiento, representante_establecimiento) " + "values('" + establecimiento.getNombre_establecimiento() + "', '" + establecimiento.getRuc_establecimiento() + "', '" + establecimiento.getDireccion_establecimiento() + "', '" + establecimiento.getCiudad().getId_ciudad() + "', '" + establecimiento.getTelefono_establecimiento() + "', '" + establecimiento.getRepresentante_establecimiento() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Establecimientos buscarId(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos e, ciudades c where e.id_ciudad=c.id_ciudad and e.id_establecimiento ='" + establecimiento.getId_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                    establecimiento.setRuc_establecimiento(rs.getString("ruc_establecimiento"));
                    establecimiento.setDireccion_establecimiento(rs.getString("direccion_establecimiento"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    establecimiento.setCiudad(ciudad);
                    establecimiento.setTelefono_establecimiento(rs.getString("telefono_establecimiento"));
                    establecimiento.setRepresentante_establecimiento(rs.getString("representante_establecimiento"));
                } else {
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");
                    establecimiento.setRuc_establecimiento("");
                    establecimiento.setDireccion_establecimiento("");
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    establecimiento.setCiudad(ciudad);
                    establecimiento.setTelefono_establecimiento("");
                    establecimiento.setRepresentante_establecimiento("");
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from establecimientos e left join ciudades c on e.id_ciudad=c.id_ciudad where upper(nombre_establecimiento) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_establecimiento offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_establecimiento") + "</td>"
                                + "<td>" + rs.getString("nombre_establecimiento") + "</td>"
                                + "<td>" + rs.getString("ruc_establecimiento") + "</td>"
                                + "<td>" + rs.getString("direccion_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("id_ciudad") + "</td>"
                                + "<td>" + rs.getString("telefono_establecimiento") + "</td>"
                                + "<td>" + rs.getString("representante_establecimiento") + "</td>"
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

    public static boolean modificar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update establecimientos set nombre_establecimiento= '" + establecimiento.getNombre_establecimiento() + "', ruc_establecimiento= '" + establecimiento.getRuc_establecimiento() + "', direccion_establecimiento= '" + establecimiento.getDireccion_establecimiento() + "', id_ciudad= '" + establecimiento.getCiudad().getId_ciudad() + "', telefono_establecimiento= '" + establecimiento.getTelefono_establecimiento() + "', representante_establecimiento= '" + establecimiento.getRepresentante_establecimiento() + "'" + " where id_establecimiento=" + establecimiento.getId_establecimiento();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Establecimientos establecimiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from establecimientos where id_establecimiento=" + establecimiento.getId_establecimiento();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    //Aqui la función buscarEstablecimiento buscara los nombres para ver si no se repiten
    public static Establecimientos buscarEstablecimiento(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos where nombre_establecimiento ='" + establecimiento.getNombre_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    establecimiento.setId_establecimiento(0);

                } else {

                    establecimiento.setId_establecimiento(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }

    public static Establecimientos buscarRuc(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos where ruc_establecimiento ='" + establecimiento.getRuc_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    establecimiento.setId_establecimiento(0);

                } else {

                    establecimiento.setId_establecimiento(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }

   public static Establecimientos buscarRepresentante(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos where representante_establecimiento ='" + establecimiento.getRepresentante_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    establecimiento.setId_establecimiento(0);

                } else {

                    establecimiento.setId_establecimiento(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    } 
   
   public static Establecimientos buscarTelefono(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos where telefono_establecimiento ='" + establecimiento.getTelefono_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    establecimiento.setId_establecimiento(0);

                } else {

                    establecimiento.setId_establecimiento(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }
   
   public static Establecimientos buscarDireccion(Establecimientos establecimiento) {

        if (Conexion.conectar()) {
            String sql = "select * from establecimientos where direccion_establecimiento ='" + establecimiento.getDireccion_establecimiento() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    establecimiento.setId_establecimiento(0);

                } else {

                    establecimiento.setId_establecimiento(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return establecimiento;
    }
}
