

package controladores;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Pedidos;
import modelos.Productos;
import modelos.DetallesPedidos;
import utiles.Conexion;
import utiles.Utiles;

public class DetallesPedidosControlador {
    
    public static DetallesPedidos buscarId(int id) {
        DetallesPedidos detallepedido = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespedidos dp "+
                             "left join pedidos p on p.id_pedido=dp.id_pedido "+
                             "left join productos pr on pr.id_producto=dp.id_producto "+
                             "where dp.id_detallepedido=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallepedido = new DetallesPedidos();
                        detallepedido.setId_detallepedido(rs.getInt("id_detallepedido"));
                        detallepedido.setCantidad_productopedido(rs.getInt("cantidad_productopedido"));
                        
                        Productos producto = new Productos();
                       producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        detallepedido.setProducto(producto);
                        
                        Pedidos pedido = new Pedidos();
                        pedido.setId_pedido(rs.getInt("id_pedido"));
                        detallepedido.setPedido(pedido);
                        
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallepedido;
    }
    
    public static String buscarIdPedido(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespedidos dp "+
                        "left join pedidos p on p.id_pedido=dp.id_pedido "+
                        "left join productos pr on pr.id_producto=dp.id_producto "+
                        "where dp.id_pedido="+id+" "+
                        "order by id_detallepedido";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_productopedido");
                        total = total.add(cantidad);
                       // System.out.println("total"+total);
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detallepedido") + "</td>"
                               + "<td>" + rs.getString("id_producto") + "</td>"
                               + "<td>" + rs.getString("nombre_producto") + "</td>" 
                               + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"
                                + "<button onclick='editarLinea("+rs.getString("id_detallepedido")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }else{
                        tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
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
    
    public static String buscarNombre(String nombre, int pagina ) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallespedidos dp "+
                        "left join pedidos p on p.id_pedido=dp.id_pedido "+
                        "left join productos pr on pr.id_producto=dp.id_producto "+
                        "where upper(pr.nombre_producto) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_detallepedido "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_detallepedido") + "</td>"
                               + "<td>" + rs.getString("id_pedido") + "</td>"
                               + "<td>" + rs.getString("id_producto") + "</td>"
                               + "<td>" + rs.getString("nombre_producto") + "</td>"
                               + "<td>" + rs.getInt("cantidad_productopedido") + "</td>" 
                               + "</tr>";
                    }
                    if(tabla.equals("")){
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

    public static boolean agregar(DetallesPedidos detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallespedidos "
                    + "(id_pedido,id_producto,cantidad_productopedido) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedido.getPedido().getId_pedido());
                ps.setInt(2, detallepedido.getProducto().getId_producto());
                ps.setInt(3, detallepedido.getCantidad_productopedido());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean modificar(DetallesPedidos detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallespedidos set "
                    + "id_pedido=?,"
                    + "id_producto=?,"
                    + "cantidad_productopedido=? "
                    + "where id_detallepedido=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedido.getPedido().getId_pedido());
                ps.setInt(2, detallepedido.getProducto().getId_producto());
                ps.setInt(3, detallepedido.getCantidad_productopedido());
                ps.setInt(4,detallepedido.getId_detallepedido());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(DetallesPedidos detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespedidos where id_detallepedido=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallepedido.getId_detallepedido());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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
    
    public static boolean eliminarPedidoPedido(Pedidos pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespedidos where id_pedido=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pedido.getId_pedido());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + pedido.getId_pedido());
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
