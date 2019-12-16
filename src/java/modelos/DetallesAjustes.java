/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO
 */
public class DetallesAjustes {

    private int id_ajuste_stock_detalle;
    private Ajustes ajuste;
    private int cantidad_ajuste_stock;
    private Productos producto;
    //private Productos producto;

    public DetallesAjustes() {
    }

    public DetallesAjustes(int id_ajuste_stock_detalle, Ajustes ajuste, int cantidad_ajuste_stock, Productos producto) {
        this.id_ajuste_stock_detalle = id_ajuste_stock_detalle;
        this.ajuste = ajuste;
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
        this.producto = producto;
    }

    public int getId_ajuste_stock_detalle() {
        return id_ajuste_stock_detalle;
    }

    public void setId_ajuste_stock_detalle(int id_ajuste_stock_detalle) {
        this.id_ajuste_stock_detalle = id_ajuste_stock_detalle;
    }

    public Ajustes getAjuste() {
        return ajuste;
    }

    public void setAjuste(Ajustes ajuste) {
        this.ajuste = ajuste;
    }

    public int getCantidad_ajuste_stock() {
        return cantidad_ajuste_stock;
    }

    public void setCantidad_ajuste_stock(int cantidad_ajuste_stock) {
        this.cantidad_ajuste_stock = cantidad_ajuste_stock;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    
}
