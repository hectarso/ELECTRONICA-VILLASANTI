      
package controladores;
import modelos.Estados_Civiles;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import utiles.Utiles;

public class Estados_CivilesControlador {
    public static boolean agregar(Estados_Civiles estadocivil){
        boolean valor=false;
        if (Conexion.conectar()) {
            String sql = "insert into estados_civiles(nombre_estadocivil) " + "values('" + estadocivil.getNombre_estadocivil() + "')" ;
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
    public static Estados_Civiles buscarId(Estados_Civiles estadocivil){
        
        if (Conexion.conectar()) {
            String sql = "select * from estados_civiles where id_estadocivil ='" + estadocivil.getId_estadocivil() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                }else {
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");
                    //return null;
                    //return estadocivil;
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return estadocivil;
    }
    
     public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from estados_civiles where upper(nombre_estadocivil) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_estadocivil offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_estadocivil") + "</td>"
                                + "<td>" + rs.getString("nombre_estadocivil") + "</td>"
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
    
    public static boolean modificar(Estados_Civiles estadocivil) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update estados_civiles set nombre_estadocivil= '" + estadocivil.getNombre_estadocivil() + "'" + " where id_estadocivil=" + estadocivil.getId_estadocivil();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Estados_Civiles estadocivil) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from estados_civiles where id_estadocivil=" + estadocivil.getId_estadocivil();
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
