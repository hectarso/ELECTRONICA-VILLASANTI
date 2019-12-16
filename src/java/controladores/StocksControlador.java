/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Administrador
 */
import modelos.Stocks;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Productos;

import modelos.Marcas;

public class StocksControlador {
  public static boolean agregar(Stocks stock) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "Select * from stocks where  "
                    + "id_producto='" + stock.getProducto().getId_producto() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (!(rs.next())) {
                    String sql2 = "insert into stocks(id_producto, cantidad_stock)"
                            + "values('" + stock.getProducto().getId_producto() + "',"
                            + "'" + stock.getCantidad_stock() + "')";
                    System.out.println("sql" + sql2);
                    try {
                        Conexion.getSt().executeUpdate(sql2);
                        valor = true;
                    } catch (SQLException ex2) {
                        System.err.println("Error:" + ex2);
                    }

                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return valor;
    }
   
//  
//    public static boolean descontar(Stocks stock){
//        boolean valor = false;
//        if (Conexion.conectar()){ 
//            String sql = "select * from stocks where "
//                    + "id_producto='" + stock.getProducto().getId_producto() + "'" + " and cantidad_stock >= '" + stock.getCantidad_stock()+ "' ";
//            
//            /*String sql = "select * from stock s left join articulos fdv on fdv.id_articulo=s.id_articulo where "
//                    + "s.id_articulo='" + stock.getArticulo().getId_articulo() + "'" + " and cantidad_stock >= '" + stock.getCantidad_stock()+ "' ";*/
//                    
//            System.out.println("descontar" + sql);
//            try {
//                ResultSet rs = Conexion.getSt().executeQuery(sql);
//                if ((rs.next())) {
//                    String sql2 = "update stocks set cantidad_stock = cantidad_stock - '" + stock.getCantidad_stock()+ "' "
//              
//                    + " where id_producto=" + stock.getProducto().getId_producto();
//                   System.out.println("SQLSTOCK " + sql2);
//            try {
//                Conexion.getSt().executeUpdate(sql2);
//                valor = true;
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//                }else{
//                    stock.setCantidad_stock(-1);
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error: " + ex);
//            }       
//        }
//        
//        return valor;
//        
//    }
      
    
        public static boolean sumar(Stocks stock){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update stocks set cantidad_stock= cantidad_stock + '" + stock.getCantidad_stock()+ "' "
              
                    + " where id_producto=" + stock.getProducto().getId_producto();
                   System.out.println("SQLSTOCK " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
         public static Stocks buscarId(Stocks stock) {

        if (Conexion.conectar()) {
            String sql = "select * from stocks where "
                    + "id_producto='" + stock.getProducto().getId_producto() + "'" + " and cantidad_stock >= '" + stock.getCantidad_stock()+ "' ";
            System.out.println("sql"+sql); 
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                  
                } else {
                 stock.setCantidad_stock(-1);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return stock;
    }
        
//        public static boolean descontarPedido(Stocks stock){
//        boolean valor = false;
//        if (Conexion.conectar()){ 
//            String sql = "select * from stocks where "
//                    + "id_producto='" + stock.getProducto().getId_producto() + "'" + " and cantidad_stock >= '" + stock.getCantidad_stock()+ "' ";
//            
//            /*String sql = "select * from stock s left join articulos fdv on fdv.id_articulo=s.id_articulo where "
//                    + "s.id_articulo='" + stock.getArticulo().getId_articulo() + "'" + " and cantidad_stock >= '" + stock.getCantidad_stock()+ "' ";*/
//                    
//            System.out.println("descontar" + sql);
//            try {
//                ResultSet rs = Conexion.getSt().executeQuery(sql);
//                if ((rs.next())) {
//                    //String sql2 = "update stocks set cantidad_stock = cantidad_stock - '" + stock.getCantidad_stock()+ "' "
//              
//                //    + " where id_producto=" + stock.getProducto().getId_producto();
//                 //  System.out.println("SQLSTOCK " + sql2);
//            try {
//              //  Conexion.getSt().executeUpdate(sql2);
//                valor = true;
//                
//          //  } catch (SQLException ex) {
//                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//                }else{
//                    stock.setCantidad_stock(-1);
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error: " + ex);
//            }       
//        }
//        
//        return valor;
//        
//    }
        
          public static boolean descontar(Stocks stock){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update stocks set cantidad_stock= cantidad_stock - " + stock.getCantidad_stock()+ " "
              
                    + " where id_producto=" + stock.getProducto().getId_producto();
                   System.out.println("SQLSTOCK " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
          
          
    
     /* 
    public static boolean eliminar(Stock stock){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from stocks where id_stock=" + stock.getId_stock();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(StockControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Stock buscarId(Stock stock) {
        if (Conexion.conectar()){
            String sql = "select * from stocks a, marcas m, categorias c where a.id_marca = m.id_marca and a.id_categoria = c.id_categoria and id_stock ='"+stock.getId_stock()+"'";
            System.out.println("sql "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    stock.setId_stock(rs.getInt("id_stock"));
                    stock.setNombre_stock(rs.getString("nombre_stock"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    stock.setMarca(marca);
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                    stock.setCategoria(categoria);
                    stock.setPrecio_unitario(rs.getInt("precio_unitario"));
                    stock.setPrecio_venta(rs.getInt("precio_venta"));
                    stock.setPrecio_compra(rs.getInt("precio_compra"));
                    stock.setDescripcion_stock(rs.getString("descripcion_stock"));
                    stock.setIva_5(rs.getString("iva_5"));
                    stock.setIva_10(rs.getString("iva_10"));
                    stock.setExenta(rs.getString("exenta"));
                    stock.setCodigo_stock(rs.getString("codigo_stock"));
                } else {
                    stock.setId_stock(0);
                    stock.setNombre_stock("");
                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");
                    stock.setMarca(marca);
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");
                    stock.setCategoria(categoria);
                    stock.setDescripcion_stock("");
                    stock.setPrecio_unitario(0);
                    stock.setPrecio_venta(0);
                    stock.setPrecio_compra(0);
                    stock.setIva_5("");
                    stock.setIva_10("");
                    stock.setExenta("");
                    stock.setCodigo_stock("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return stock;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from stocks where upper(nombre_stock) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_stock offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_stock") + "</td>"
                                + "<td>" + rs.getString("nombre_stock") + "</td>"
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
    
    public static Stock buscarCodigo(Stock stock) {
        if (Conexion.conectar()){
            String sql = "select * from stocks where codigo_stock ='"+stock.getCodigo_stock()+"'";
            System.out.println("sql "+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    stock.setId_stock(0);
                    
                } else {
                    stock.setId_stock(1);
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return stock;
    }
    */  
        //copia
        /*public static boolean agregar(Stocks stock) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from stocks where "
                    + "id_ubicacion='" + stock.getUbicacion().getId_ubicacion() + "', "
                    + "id_producto='" + stock.getProducto().getId_producto() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (!(rs.next())) {
                    String sql2 = "insert into stocks(id_ubicacion, id_producto, cantidad_stock)"
                            + "values('" + stock.getUbicacion().getId_ubicacion()+ "'," 
                            + "'" + stock.getProducto().getId_producto() + "',"
                            + "'" + stock.getCantidad_stock() + "')";
                    System.out.println("sql" + sql2);
                    try {
                        Conexion.getSt().executeUpdate(sql2);
                        valor = true;
                    } catch (SQLException ex2) {
                        System.err.println("Error:" + ex2);
                    }

                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return valor;
    }
   
  
    public static boolean descontar(Stocks stock){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update stocks set cantidad_stock= cantidad_stock - '" + stock.getCantidad_stock()+ "' "
              
                    + " where id_ubicacion=" + stock.getUbicacion().getId_ubicacion() + " and id_producto=" + stock.getProducto().getId_producto();
                   System.out.println("SQLSTOCK " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
        public static boolean sumar(Stocks stock){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update stocks set cantidad_stock= cantidad_stock + '" + stock.getCantidad_stock()+ "'"
              
                    + " where id_ubicacion= "+ stock.getUbicacion().getId_ubicacion() + " and id_producto=" + stock.getProducto().getId_producto();
                   System.out.println("SQLSTOCK " + sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(StocksControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;*/
        
    
}
