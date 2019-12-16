package controladores;

import modelos.Proveedores;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;
import utiles.Utiles;

public class ProveedoresControlador {

    public static boolean agregar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into proveedores(nombre_proveedor, ruc_proveedor, direccion_proveedor , id_ciudad, correo_proveedor, telefono1_proveedor, telefono2_proveedor) " + "values('" + proveedor.getNombre_proveedor() + "', '" + proveedor.getRuc_proveedor() + "', '" + proveedor.getDireccion_proveedor() + "', '" + proveedor.getCiudad().getId_ciudad() + "', '" + proveedor.getCorreo_proveedor() + "', '" + proveedor.getTelefono1_proveedor() + "', '" + proveedor.getTelefono2_proveedor() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Proveedores buscarId(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores p, ciudades c where p.id_ciudad=c.id_ciudad and p.id_proveedor ='" + proveedor.getId_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    proveedor.setCiudad(ciudad);
                    proveedor.setCorreo_proveedor(rs.getString("correo_proveedor"));
                    proveedor.setTelefono1_proveedor(rs.getString("telefono1_proveedor"));
                    proveedor.setTelefono2_proveedor(rs.getString("telefono2_proveedor"));
                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                    proveedor.setRuc_proveedor("");
                    proveedor.setDireccion_proveedor("");
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    proveedor.setCiudad(ciudad);
                    proveedor.setCorreo_proveedor("");
                    proveedor.setTelefono1_proveedor("");
                    proveedor.setTelefono2_proveedor("");
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from proveedores p left join ciudades c on p.id_ciudad=c.id_ciudad where upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                                + "<td>" + rs.getString("direccion_proveedor") + "</td>"
                                //+ "<td>" + rs.getString("id_ciudad") + "</td>"
                                + "<td>" + rs.getString("correo_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono1_proveedor") + "</td>"
                                + "<td>" + rs.getString("telefono2_proveedor") + "</td>"
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

    public static boolean modificar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update proveedores set nombre_proveedor= '" + proveedor.getNombre_proveedor() + "', ruc_proveedor= '" + proveedor.getRuc_proveedor() + "', direccion_proveedor= '" + proveedor.getDireccion_proveedor() + "', id_ciudad= '" + proveedor.getCiudad().getId_ciudad() + "', correo_proveedor= '" + proveedor.getCorreo_proveedor() + "', telefono1_proveedor= '" + proveedor.getTelefono1_proveedor() + "', telefono2_proveedor= '" + proveedor.getTelefono2_proveedor() + "'" + " where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Proveedores proveedor) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from proveedores where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    //Aqui la función buscarProveedor buscara los nombres para ver si no se repiten
    public static Proveedores buscarProveedor(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores where nombre_proveedor ='" + proveedor.getNombre_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    proveedor.setId_proveedor(0);

                } else {

                    proveedor.setId_proveedor(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }

    public static Proveedores buscarRuc(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores where ruc_proveedor ='" + proveedor.getRuc_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    proveedor.setId_proveedor(0);

                } else {

                    proveedor.setId_proveedor(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }

   public static Proveedores buscarCorreo(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores where correo_proveedor ='" + proveedor.getCorreo_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    proveedor.setId_proveedor(0);

                } else {

                    proveedor.setId_proveedor(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    } 
   
   public static Proveedores buscarTelefono1(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores where telefono1_proveedor ='" + proveedor.getTelefono1_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    proveedor.setId_proveedor(0);

                } else {

                    proveedor.setId_proveedor(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }
   
   public static Proveedores buscarTelefono2(Proveedores proveedor) {

        if (Conexion.conectar()) {
            String sql = "select * from proveedores where telefono2_proveedor ='" + proveedor.getTelefono2_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    proveedor.setId_proveedor(0);

                } else {

                    proveedor.setId_proveedor(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return proveedor;
    }
}
