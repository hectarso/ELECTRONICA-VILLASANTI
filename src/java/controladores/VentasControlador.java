package controladores;

import java.math.BigDecimal;
import java.sql.Date;
import modelos.Ventas;
import modelos.Timbrados;
import modelos.Establecimientos;
import modelos.Clientes;
import utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import modelos.DetallesCajas;
import modelos.Puestos;
import utiles.Utiles;

public class VentasControlador {

    public static Ventas buscarId(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v "
                        + "left join clientes c on v.id_cliente=c.id_cliente "
                        + "left join timbrados t on v.id_timbrado=t.id_timbrado "
                        + "left join establecimientos e on v.id_establecimiento=e.id_establecimiento "
                        + "left join puestos pu on v.id_puesto=pu.id_puesto "
                        + "where estado_venta !='COBRADO' and id_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        ventas.setFecha_venta(rs.getDate("fecha_venta"));
                        Timbrados timbrado = new Timbrados();
                        timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                        timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                        ventas.setEstado_venta(rs.getString("estado_venta"));
                        Establecimientos establecimiento = new Establecimientos();
                        establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                        /*establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                        establecimiento.setRuc_establecimiento(rs.getString("ruc_establecimiento"));*/
                        Puestos puesto = new Puestos();
                        puesto.setId_puesto(rs.getInt("id_puesto"));
                        ventas.setPuesto(puesto);
                        ventas.setEstablecimiento(establecimiento);
                        ventas.setTimbrado(timbrado);
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        ventas.setCliente(cliente);
                        ventas.setNumero_factura(rs.getInt("numero_factura"));
                    } else {

                        try {

                            String sqli = "SELECT COUNT (*) AS Ultimo, COUNT(numero_factura) FROM ventas";
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    ventas = new Ventas();
                                    num = rsi.getInt("Ultimo");
                                    ventas.setId_venta(0);
                                    ventas.setNumero_factura(num + 1);

                                    ventas.setEstado_venta("PENDIENTE");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    ventas.setFecha_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setId_cliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    ventas.setCliente(cliente);

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setId_timbrado(1);
                                    //timbrado.setNumero_timbrado("");
                                    ventas.setTimbrado(timbrado);

                                    Puestos puesto = new Puestos();
                                    puesto.setId_puesto(1);
                                    ventas.setPuesto(puesto);

                                    Establecimientos establecimiento = new Establecimientos();
                                    establecimiento.setId_establecimiento(1);
                                    ventas.setEstablecimiento(establecimiento);

                                } else {
                                    num = 1;
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());
                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v "
                        + "left join clientes c on c.id_cliente=v.id_cliente "
                        + "left join timbrados t on t.id_timbrado=v.id_timbrado "
                        + "left join establecimientos e on e.id_establecimiento=v.id_establecimiento "
                        + "left join puestos pu on pu.id_puesto=v.id_puesto "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "and v.estado_venta !='COBRADO' order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                               // + "<td>" + rs.getString("id_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("nombre_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("ruc_establecimiento") + "</td>"
                               // + "<td>" + rs.getString("id_puesto") + "</td>"
                               // + "<td>" + rs.getString("id_timbrado") + "</td>"
                               // + "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("estado_venta") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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

    public static boolean agregar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = venta.getFecha_venta();
            int v2 = venta.getCliente().getId_cliente();
            int v3 = venta.getTimbrado().getId_timbrado();
            int v4 = venta.getNumero_factura();
            String v5 = venta.getEstado_venta();
            int v6 = venta.getPuesto().getId_puesto();
            int v7 = venta.getEstablecimiento().getId_establecimiento();

            String sql = "insert into ventas(fecha_venta ,id_cliente, id_timbrado, numero_factura, estado_venta, id_establecimiento, id_puesto) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "','" + v6 + "','" + v7 + /*"','" + v8 /*+ "','" + v9 */ "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set id_cliente=?,"
                    + "id_timbrado=?,"
                    + "numero_factura=?,"
                    //+ "estado_venta=?,"
                    + "id_establecimiento=?,"
                    + "id_puesto=? "
                    + "where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, venta.getCliente().getId_cliente());
                ps.setInt(2, venta.getTimbrado().getId_timbrado());
                ps.setInt(3, venta.getNumero_factura());
                //ps.setString(4, venta.getEstado_venta());
                ps.setInt(4, venta.getEstablecimiento().getId_establecimiento());
                ps.setInt(5, venta.getPuesto().getId_puesto());
                ps.setInt(6, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

    public static boolean eliminar(Ventas venta) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

    public static boolean modificarestado(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='ANULADO'  "
                    + " where id_venta=" + venta.getId_venta();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
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
    
    public static boolean modificarestadocobro(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='COBRADO'"
                    + " where id_venta=" + venta.getId_venta();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
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
    
    public static Ventas buscarNumFactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura) FROM ventas ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();

                        ventas.setNumero_factura(rsi.getInt("Ultimo"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
 /*public static Ventas buscarTotalfactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select v.numero_factura,v.id_venta,SUM(total) as total from ventas v "
                        + "left join detallesventas dv on dv.id_venta=v.id_venta "
                        + "where v.id_venta=? "
                        + "group by v.id_venta";

                System.out.println("sss" + id);

                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rsi.getInt("id_venta"));
                        ventas.setNumero_factura(rsi.getInt("numero_factura"));
                        ventas.setTotal(rsi.getInt("total"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }*/
    public static Ventas buscarId3(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    System.out.println("sqltotal " + ps);
                    ResultSet rs = ps.executeQuery();

                    //BigDecimal iva = BigDecimal.ZERO;
                    if (rs.next()) {
                        int cantidad = rs.getInt("cantidad_producto_venta");
                        int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_venta"));
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    int totalt = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;

                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad1 = rs.getInt("cantidad_producto_venta");
                        int venta = rs.getInt("precio_venta_producto");
                        if (iva == 0) {

                            totalexentas = venta * cantidad1;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad1;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad1;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;

                        // System.out.println("total"+total);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                        //iva = iva.add(subiva);
                        //subtotaliva
                    }

                    String sql2 = "select v.id_venta, v.numero_factura, sum(precio_venta_producto * " + cantidad + ") +" + totales + "as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta, v.numero_factura";
                    System.out.println("total " + sql2);
                    try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                        ResultSet rs1 = ps1.executeQuery();
                        if (rs1.next()) {
                            int total_pagar = rs1.getInt("TotalaPagar");
                            ventas = new Ventas();
                            ventas.setId_venta(id);
                            ventas.setNumero_factura(rs.getInt("numero_factura"));
                            ventas.setTotal(total_pagar);

                        }
                        System.out.println("Total a Pagar " + sql2);
                        System.out.println(rs1.getString("TotalaPagar"));
                        ps1.close();
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());

                    }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static Ventas buscartotal(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                    int factura = rs.getInt("numero_factura");
                    int totales = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_producto_venta");
                        int venta = rs.getInt("precio_venta_producto");
                        if (iva == 0) {

                            totalexentas = venta * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5/21;
                        liqiva10 = total10/11;

                        totales = liqiva5 + liqiva10;
   
                    }
                       String sql2 = "select v.id_venta, v.numero_factura, sum(precio_venta_producto * to_number(cantidad_producto_venta,'999999999999D99')) as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta";
                System.out.println("total " + sql2);
                try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        int total_pagar = rs1.getInt("TotalaPagar");
                        //int factura = rs1.getInt("numero_factura");
                        ventas = new Ventas();
                        ventas.setId_venta(id);
                        ventas.setNumero_factura(factura);
                        ventas.setTotal(total_pagar);

                    }
                    System.out.println("Total a Pagar " + sql2);
                    System.out.println(rs1.getString("TotalaPagar"));
                    ps1.close();
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());

                }
                    }
                    ps.close();
                }
                
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
    
    public static Ventas buscartotalbien(int id) {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesventas dv "
                        + "left join ventas v on v.id_venta=dv.id_venta "
                        + "left join productos p on p.id_producto=dv.id_producto "
                        + "where dv.id_venta=" + id + " "
                        + "order by id_detalle_venta";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    //BigDecimal iva = BigDecimal.ZERO;
                    int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_venta"));
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    int totalt = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    
                    while (rs.next()) {
                        iva = rs.getInt("iva_producto");
                        int cantidad1 = rs.getInt("cantidad_producto_venta");
                        int venta = rs.getInt("precio_venta_producto");
                        if (iva == 0) {

                            totalexentas = venta * cantidad1;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = venta * cantidad1;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = venta * cantidad1;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 /21;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;

                    }
                String sqlbien = "select v.id_venta, v.numero_factura, sum(precio_venta_producto * to_number(cantidad_producto_venta,'999999999999D99')) + " + totales + " as TotalaPagar from detallesventas dv left join ventas v on v.id_venta=dv.id_venta left join productos p on p.id_producto=dv.id_producto where dv.id_venta=" + id + " group by v.id_venta";
                System.out.println("total " + sqlbien);
                
                try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sqlbien)) {

                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        int total_pagar = rs1.getInt("TotalaPagar");
                        //int factura = rs1.getInt("numero_factura");
                        ventas = new Ventas();
                        ventas.setId_venta(id);
                        ventas.setNumero_factura(rs.getInt("numero_factura"));
                        ventas.setTotal(total_pagar);

                    }
                    System.out.println("Total a Pagar " + sqlbien);
                    System.out.println(rs1.getString("TotalaPagar"));
                    ps1.close();
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());

                }
                    }
                        
                    ps.close();
                    
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
}
