package controladores;

import modelos.Productos;
import modelos.Marcas;
import modelos.Categorias;
import modelos.Ubicaciones;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Ivas;
import utiles.Utiles;

public class ProductosControlador {

    public static boolean agregar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into productos(nombre_producto, "
                    + "id_marca, id_categoria, id_ubicacion, "
                    + "stockmin_producto, stockmax_producto,"
                    + " codigo_producto, precio_compra_producto,"
                    + " precio_venta_producto, iva_producto) " 
                    + "values('" + producto.getNombre_producto() + "', '" 
                    + producto.getMarca().getId_marca() + "', '" 
                    + producto.getCategoria().getId_categoria() + "', '" 
                    + producto.getUbicacion().getId_ubicacion() + "', '" 
                    + producto.getStockmin_producto() + "', '" 
                    + producto.getStockmax_producto() + "', '" 
                    + producto.getCodigo_producto() + "', '" 
                    + producto.getPrecio_compra_producto() + "', '" 
                    + producto.getPrecio_venta_producto() + "', '" 
                    + producto.getIva_producto() + "')";
                    //+ producto.getIva().getId_iva() + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if(keyset.next()){
                    int id_producto=keyset.getInt(1);
                    producto.setId_producto(id_producto);
                                      
                }
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Productos buscarId(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos p, marcas m,"
                    + " categorias c, ubicaciones r where p.id_marca=m.id_marca "
                    + "and p.id_categoria=c.id_categoria and p.id_ubicacion=r.id_ubicacion "
                    + "and p.id_producto ='" + producto.getId_producto() + "'";
            System.out.println("sql"+ sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    producto.setMarca(marca);
                    
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(rs.getInt("id_categoria"));
                    categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                    producto.setCategoria(categoria);
                    Ubicaciones ubicacion = new Ubicaciones();
                    ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
                    ubicacion.setNombre_ubicacion(rs.getString("nombre_ubicacion"));
                    producto.setUbicacion(ubicacion);
                    producto.setStockmin_producto(rs.getInt("stockmin_producto"));
                    producto.setStockmax_producto(rs.getInt("stockmax_producto"));
                    producto.setCodigo_producto(rs.getString("codigo_producto"));
                    producto.setPrecio_compra_producto(rs.getInt("precio_compra_producto"));
                    producto.setPrecio_venta_producto(rs.getInt("precio_venta_producto"));
                    producto.setIva_producto(rs.getInt("iva_producto"));
                } else {
                    producto.setId_producto(0);
                    producto.setNombre_producto("");
                    producto.setDescripcion_producto("");
                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");
                    Categorias categoria = new Categorias();
                    categoria.setId_categoria(0);
                    categoria.setNombre_categoria("");
                    Ubicaciones ubicacion = new Ubicaciones();
                    ubicacion.setId_ubicacion(0);
                    ubicacion.setNombre_ubicacion("");
                    producto.setStockmin_producto(0);
                    producto.setStockmax_producto(0);
                    producto.setCodigo_producto("");
                    producto.setPrecio_compra_producto(0);
                    producto.setPrecio_venta_producto(0);
                    producto.setIva_producto(0);
                    producto.setMarca(marca);
                    producto.setCategoria(categoria);
                    producto.setUbicacion(ubicacion);
                    //producto.setIva(iva);
                    //return null;
                    //return cliente;
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

    public static String buscarNombre(String nombre, int pagina) {

        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        /*if (Conexion.conectar()) {

            try {
                System.out.println(nombre);
                String sql = "select * from productos p left join marcas m on 
                p.id_marca=m.id_marca left join categorias c on p.id_categoria=c.id_categoria 
                left join ubicaciones r on p.id_ubicacion=r.id_ubicacion 
                where upper(nombre_producto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;

                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                //+ "<td>" + rs.getString("id_marca") + "</td>"
                                //+ "<td>" + rs.getString("id_categoria") + "</td>"
                                //+ "<td>" + rs.getString("id_ubicacion") + "</td>"
                                //+ "<td>" + rs.getString("id_iva") + "</td>"
                                //+ "<td>" + rs.getString("stockmin_producto") + "</td>"
                                //+ "<td>" + rs.getString("stockmax_producto") + "</td>"
                                + "<td>" + rs.getString("codigo_producto") + "</td>"
                                //+ "<td>" + rs.getString("precio_compra_producto") + "</td>"
                                + "<td>" + rs.getString("precio_venta_producto") + "</td>"
                                //+ "<td>" + rs.getString("iva_producto") + "</td>"
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
        }*/
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                /*String sql = "select * from productos a, stocks t where "
                        + "a.id_producto = t.id_producto and upper(nombre_producto) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by a.id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;*/
                 String sql = "select * from productos p"
                        + " left join stocks s on p.id_producto = s.id_producto"
                        + " where upper(nombre_producto) like '%"
                        + nombre.toUpperCase() + "%'"
                        + "order by p.id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_producto") + "</td>"
                                + "<td>" + rs.getString("nombre_producto") + "</td>"
                                + "<td>" + rs.getString("cantidad_stock") + "</td>"
                                + "<td>" + rs.getString("precio_venta_producto") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=3> No existen registros...</td></tr>";
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

    public static boolean modificar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update productos set nombre_producto= '" + producto.getNombre_producto() 
                    + "', id_marca= '" + producto.getMarca().getId_marca() 
                    + "', id_categoria= '" + producto.getCategoria().getId_categoria() 
                    + "', id_ubicacion= '" + producto.getUbicacion().getId_ubicacion() 
                    + "', stockmin_producto= '" + producto.getStockmin_producto() 
                    + "', stockmax_producto= '" + producto.getStockmax_producto() 
                    + "', codigo_producto= '" + producto.getCodigo_producto() 
                    + "', precio_compra_producto= '" + producto.getPrecio_compra_producto() 
                    + "', precio_venta_producto= '" + producto.getPrecio_venta_producto()
                    + "', iva_producto= '" + producto.getIva_producto() 
                    + "'" + " where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Productos producto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from productos where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    //Aqui la función buscarProducto buscara los nombres de los productos para ver si no se repiten
    public static Productos buscarProducto(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos where nombre_producto ='" + producto.getNombre_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    producto.setId_producto(0);

                } else {

                    producto.setId_producto(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }
    //buscar codigo
    public static Productos buscarCodigo(Productos producto) {

        if (Conexion.conectar()) {
            String sql = "select * from productos where codigo_producto ='" + producto.getCodigo_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {

                    producto.setId_producto(0);

                } else {

                    producto.setId_producto(-1);

                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return producto;
    }

}
