      
package controladores;
import modelos.Rubros;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utiles.Utiles;

public class RubrosControlador {
    public static boolean agregar(Rubros rubro){
        boolean valor=false;
        if (Conexion.conectar()) {
            String sql = "insert into rubros(nombre_rubro) " + "values('" + rubro.getNombre_rubro() + "')" ;
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
    public static Rubros buscarId(Rubros rubro){
        
        if (Conexion.conectar()) {
            String sql = "select * from rubros where id_rubro ='" + rubro.getId_rubro() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    rubro.setId_rubro(rs.getInt("id_rubro"));
                    rubro.setNombre_rubro(rs.getString("nombre_rubro"));
                }else {
                    rubro.setId_rubro(0);
                    rubro.setNombre_rubro("");
                    //return null;
                    //return rubro;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return rubro;
    }
    
     public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from rubros where upper(nombre_rubro) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_rubro offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_rubro") + "</td>"
                                + "<td>" + rs.getString("nombre_rubro") + "</td>"
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
    
    public static boolean modificar(Rubros rubro) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update rubros set nombre_rubro= '" + rubro.getNombre_rubro() + "'" + " where id_rubro=" + rubro.getId_rubro();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Rubros rubro) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from rubros where id_rubro=" + rubro.getId_rubro();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
}
