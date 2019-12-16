/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO1
 */
public class Ventas {

    private int id_venta;
    private Date fecha_venta;
    private Timbrados timbrado;
    private int numero_factura;
    private Clientes cliente;
    private String estado_venta;
    private int total;
    private Establecimientos establecimiento;
    private Puestos puesto;

    public Ventas() {
    }

    public Ventas(int id_venta, Date fecha_venta, Timbrados timbrado, int numero_factura, Clientes cliente, String estado_venta, int total, Establecimientos establecimiento, Puestos puesto) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.timbrado = timbrado;
        this.numero_factura = numero_factura;
        this.cliente = cliente;
        this.estado_venta = estado_venta;
        this.total = total;
        this.establecimiento = establecimiento;
        this.puesto = puesto;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Timbrados getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrados timbrado) {
        this.timbrado = timbrado;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getEstado_venta() {
        return estado_venta;
    }

    public void setEstado_venta(String estado_venta) {
        this.estado_venta = estado_venta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    
}
