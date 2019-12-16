/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class DetallesVentas {

    private int id_detalle_venta;
    private Ventas venta;
    private Productos producto;
    private int cantidad_producto_venta;
    private int preciototal_venta;

    public DetallesVentas() {
    }

    public DetallesVentas(int id_detalle_venta, Ventas venta, Productos producto, int cantidad_producto_venta, int preciototal_venta) {
        this.id_detalle_venta = id_detalle_venta;
        this.venta = venta;
        this.producto = producto;
        this.cantidad_producto_venta = cantidad_producto_venta;
        this.preciototal_venta = preciototal_venta;
    }

    public int getId_detalle_venta() {
        return id_detalle_venta;
    }

    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_producto_venta() {
        return cantidad_producto_venta;
    }

    public void setCantidad_producto_venta(int cantidad_producto_venta) {
        this.cantidad_producto_venta = cantidad_producto_venta;
    }

    public int getPreciototal_venta() {
        return preciototal_venta;
    }

    public void setPreciototal_venta(int preciototal_venta) {
        this.preciototal_venta = preciototal_venta;
    }

    
}
