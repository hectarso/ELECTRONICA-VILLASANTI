      
package controladores;
import modelos.Clientes;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;
import utiles.Utiles;

public class ClientesControlador {
    public static boolean agregar(Clientes cliente){
        boolean valor=false;
        if (Conexion.conectar()) {
            String sql = "insert into clientes(nombre_cliente,"
                    + " apellido_cliente, id_ciudad, ruc_cliente, "
                    + "ci_cliente, correo_cliente, telefono_cliente)"
                    + " " + "values('" + cliente.getNombre_cliente() 
                    + "', '" + cliente.getApellido_cliente()
                    + "', '" + cliente.getCiudad().getId_ciudad() 
                    + "', '" + cliente.getRuc_cliente() 
                    + "', '" + cliente.getCi_cliente() 
                    + "', '" + cliente.getCorreo_cliente() 
                    + "', '" + cliente.getTelefono_cliente() + "')" ;
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
    public static Clientes buscarId(Clientes cliente){
        
        if (Conexion.conectar()) {
            String sql = "select * from clientes cli, ciudades ciu where cli.id_ciudad=ciu.id_ciudad and cli.id_cliente ='" + cliente.getId_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                    cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    cliente.setCiudad(ciudad);
                    cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                    cliente.setCi_cliente(rs.getString("ci_cliente"));
                    cliente.setCorreo_cliente(rs.getString("correo_cliente"));
                    cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                }else {
                    cliente.setId_cliente(0);
                    cliente.setNombre_cliente("");
                    cliente.setApellido_cliente("");
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    cliente.setCiudad(ciudad);
                    cliente.setRuc_cliente("");
                    cliente.setCi_cliente("");
                    cliente.setCorreo_cliente("");
                    cliente.setTelefono_cliente("");
                    //return null;
                    //return cliente;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return cliente;
    }
    
     public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from clientes cl left join ciudades ci on cl.id_ciudad=ci.id_ciudad where upper(nombre_cliente) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_cliente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("apellido_cliente") + "</td>"
                                //+ "<td>" + rs.getString("id_ciudad") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("ci_cliente") + "</td>"
                                + "<td>" + rs.getString("correo_cliente") + "</td>"
                                + "<td>" + rs.getString("telefono_cliente") + "</td>"
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
    
    public static boolean modificar(Clientes cliente) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update clientes set nombre_cliente= '" + cliente.getNombre_cliente() + "', apellido_cliente= '"+ cliente.getApellido_cliente() + "', id_ciudad= '" + cliente.getCiudad().getId_ciudad() + "', ruc_cliente= '" + cliente.getRuc_cliente() + "', ci_cliente= '" + cliente.getCi_cliente() + "', correo_cliente= '" + cliente.getCorreo_cliente() + "', telefono_cliente= '" + cliente.getTelefono_cliente() + "'" + " where id_cliente=" + cliente.getId_cliente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Clientes cliente) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from clientes where id_cliente=" + cliente.getId_cliente();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    public static Clientes buscarRuc(Clientes cliente) {

        if (Conexion.conectar()) {
            String sql = "select * from clientes where ruc_cliente ='" + cliente.getRuc_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }
    
    public static Clientes buscarCi(Clientes cliente) {

        if (Conexion.conectar()) {
            String sql = "select * from clientes where ci_cliente ='" + cliente.getCi_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }
    
    public static Clientes buscarCorreo(Clientes cliente) {

        if (Conexion.conectar()) {
            String sql = "select * from clientes where correo_cliente ='" + cliente.getCorreo_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }
    
    public static Clientes buscarTelefono(Clientes cliente) {

        if (Conexion.conectar()) {
            String sql = "select * from clientes where telefono_cliente ='" + cliente.getTelefono_cliente() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    cliente.setId_cliente(0);

                } else {

                    cliente.setId_cliente(-1);

                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return cliente;
    }
}
