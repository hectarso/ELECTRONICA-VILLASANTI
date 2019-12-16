/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

public class FacturaCompras {

    private int id_factura_compra;
    
    private Proveedores proveedor;
    private Date fecha_factura_compra;
    private Tipo_facturas tipofactura;
    
    

    
    

    public FacturaCompras() {
    }

    public FacturaCompras(int id_factura_compra, Proveedores proveedor, Date fecha_factura_compra, Tipo_facturas tipofactura) {
        this.id_factura_compra = id_factura_compra;
        this.proveedor = proveedor;
        this.fecha_factura_compra = fecha_factura_compra;
        this.tipofactura = tipofactura;
    }

    public int getId_factura_compra() {
        return id_factura_compra;
    }

    public void setId_factura_compra(int id_factura_compra) {
        this.id_factura_compra = id_factura_compra;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha_factura_compra() {
        return fecha_factura_compra;
    }

    public void setFecha_factura_compra(Date fecha_factura_compra) {
        this.fecha_factura_compra = fecha_factura_compra;
    }

    public Tipo_facturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(Tipo_facturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    

    

    

    

    

    

    


    
}
