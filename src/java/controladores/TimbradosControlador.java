      
package controladores;
//Hago las importaciones necesarias
import java.sql.Date;
import modelos.Timbrados;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import modelos.Establecimientos;
//import java.util.ArrayList;
import utiles.Utiles;

public class TimbradosControlador {
    public static boolean agregar(Timbrados timbrado){
        boolean valor=false;
        if (Conexion.conectar()) {
            String sql = "insert into timbrados(numero_timbrado, fechaini_timbrado, fechaact_timbrado, fechafin_timbrado, desde_timbrado, hasta_timbrado, estado_timbrado, id_establecimiento) " 
                    + "values('" + timbrado.getNumero_timbrado()+ "', '" 
                    + timbrado.getFechaini_timbrado() + "', '" 
                    + timbrado.getFechaact_timbrado() + "', '" 
                    + timbrado.getFechafin_timbrado() + "', '" //"', '" 
                    + timbrado.getDesde_timbrado() + "', '" 
                    + timbrado.getHasta_timbrado() + "', '" 
                    + timbrado.getEstado_timbrado() + "', '"
                    + timbrado.getEstablecimiento().getId_establecimiento() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
    public static Timbrados buscarId(Timbrados timbrado){
        
        if (Conexion.conectar()) {
            String sql = "select * from timbrados t, establecimientos e where t.id_establecimiento=e.id_establecimiento and t.id_timbrado ='" + timbrado.getId_timbrado() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) { //si encuentra un id traera todos los datos
                    timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFechaini_timbrado(rs.getDate("fechaini_timbrado"));
                    timbrado.setFechaact_timbrado(rs.getDate("fechaact_timbrado"));
                    timbrado.setFechafin_timbrado(rs.getDate("fechafin_timbrado"));
                    timbrado.setDesde_timbrado(rs.getInt("desde_timbrado"));
                    timbrado.setHasta_timbrado(rs.getInt("hasta_timbrado"));
                    timbrado.setEstado_timbrado(rs.getString("estado_timbrado"));
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                    timbrado.setEstablecimiento(establecimiento);
                }else { //si no encuentra datos, vaciara los campos y habilitara la opcion para agregar los datos
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                    timbrado.setFechaini_timbrado(null);
                    timbrado.setFechaact_timbrado(null);
                    timbrado.setFechafin_timbrado(null);
                    timbrado.setDesde_timbrado(0);
                    timbrado.setHasta_timbrado(0);
                    timbrado.setEstado_timbrado("");
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");
                    timbrado.setEstablecimiento(establecimiento);
                    
                    /*java.sql.Date fechaini_timbrado = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFechaini_timbrado(fechaini_timbrado);
                    java.sql.Date fechafin_timbrado = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFechafin_timbrado(fechafin_timbrado);
                    java.sql.Date fechaact_timbrado = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFechaact_timbrado(fechaact_timbrado);*/
                }
            } catch(SQLException ex) {
                System.err.println("Error:"+ex);
            }
        }
        return timbrado;
    }
    
     public static String buscarNumero(String numero, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(numero);
                String sql = "select * from timbrados t left join establecimientos e on t.id_establecimiento=e.id_establecimiento where upper(numero_timbrado) like '%" +
                        numero.toUpperCase() + "%'"
                        + "order by id_timbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td>" + rs.getDate("fechaini_timbrado") + "</td>"
                                + "<td>" + rs.getDate("fechaact_timbrado") + "</td>"
                                + "<td>" + rs.getDate("fechafin_timbrado") + "</td>"
                                + "<td>" + rs.getInt("desde_timbrado") + "</td>"
                                + "<td>" + rs.getInt("hasta_timbrado") + "</td>"
                                + "<td>" + rs.getString("estado_timbrado") + "</td>"
                                //+ "<td>" + rs.getString("id_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("nombre_establecimiento") + "</td>"
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
    
    public static boolean modificar(Timbrados timbrado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update timbrados set numero_timbrado= '" + timbrado.getNumero_timbrado()
                    + "', fechaini_timbrado= '" + timbrado.getFechaini_timbrado()
                    + "', fechaact_timbrado= '" + timbrado.getFechaact_timbrado()
                    + "', fechafin_timbrado= '" + timbrado.getFechafin_timbrado()
                    + "', desde_timbrado= '" + timbrado.getDesde_timbrado()
                    + "', hasta_timbrado= '" + timbrado.getHasta_timbrado()
                    + "', estado_timbrado= '" + timbrado.getEstado_timbrado()//+ "'"
                    + "', id_establecimiento= '" + timbrado.getEstablecimiento().getId_establecimiento()+ "'"
                    + " where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }
    
    public static boolean eliminar(Timbrados timbrado) {
        boolean valor =false;
        if (Conexion.conectar()) {
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
    
    public static Timbrados buscarTimbrado(Timbrados timbrado) {

        if (Conexion.conectar()) {
            String sql = "select * from timbrados where numero_timbrado ='" + timbrado.getNumero_timbrado() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    timbrado.setId_timbrado(0);

                } else {

                    timbrado.setId_timbrado(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return timbrado;
    }
}
