/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


public class FacturaDetalleCompras {

    private int id_factura_detalle_compra;
    private FacturaCompras facturacompra;
    private Productos producto;
    private int cantidad_compra;
    private int subtotal_compra;

    public FacturaDetalleCompras() {
    }

    public FacturaDetalleCompras(int id_factura_detalle_compra, FacturaCompras facturacompra, Productos producto, int cantidad_compra, int subtotal_compra) {
        this.id_factura_detalle_compra = id_factura_detalle_compra;
        this.facturacompra = facturacompra;
        this.producto = producto;
        this.cantidad_compra = cantidad_compra;
        this.subtotal_compra = subtotal_compra;
    }

    public int getId_factura_detalle_compra() {
        return id_factura_detalle_compra;
    }

    public void setId_factura_detalle_compra(int id_factura_detalle_compra) {
        this.id_factura_detalle_compra = id_factura_detalle_compra;
    }

    public FacturaCompras getFacturacompra() {
        return facturacompra;
    }

    public void setFacturacompra(FacturaCompras facturacompra) {
        this.facturacompra = facturacompra;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_compra() {
        return cantidad_compra;
    }

    public void setCantidad_compra(int cantidad_compra) {
        this.cantidad_compra = cantidad_compra;
    }

    public int getSubtotal_compra() {
        return subtotal_compra;
    }

    public void setSubtotal_compra(int subtotal_compra) {
        this.subtotal_compra = subtotal_compra;
    }

  
    

    
    
}