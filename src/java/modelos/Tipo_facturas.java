/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Tipo_facturas {
    private int id_tipo_factura;
    private String nombre_tipo_factura;

    public Tipo_facturas() {
    }

    public Tipo_facturas(int id_tipo_factura, String nombre_tipo_factura) {
        this.id_tipo_factura = id_tipo_factura;
        this.nombre_tipo_factura = nombre_tipo_factura;
    }

    
    public int getId_tipo_factura() {
        return id_tipo_factura;
    }

    public void setId_tipo_factura(int id_tipo_factura) {
        this.id_tipo_factura = id_tipo_factura;
    }

    public String getNombre_tipo_factura() {
        return nombre_tipo_factura;
    }

    public void setNombre_tipo_factura(String nombre_tipo_factura) {
        this.nombre_tipo_factura = nombre_tipo_factura;
    }
    
    
    
    
}
