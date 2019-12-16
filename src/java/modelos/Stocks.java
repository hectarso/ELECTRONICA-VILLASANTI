/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Administrador
 */
public class Stocks {
    private int id_stock;
    //private Ubicaciones ubicacion;
    private Productos producto;
    private int cantidad_stock;

    public Stocks() {
    }

    public Stocks(int id_stock, Productos producto, int cantidad_stock) {
        this.id_stock = id_stock;
        this.producto = producto;
        this.cantidad_stock = cantidad_stock;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(int cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    
}
