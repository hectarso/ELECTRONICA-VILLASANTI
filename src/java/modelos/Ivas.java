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
public class Ivas {
    private int id_iva;
    private String nombre_iva;
    private int porcentaje_iva;

    public Ivas() {
    }

    public Ivas(int id_iva, String nombre_iva, int porcentaje_iva) {
        this.id_iva = id_iva;
        this.nombre_iva = nombre_iva;
        this.porcentaje_iva = porcentaje_iva;
    }

    public int getId_iva() {
        return id_iva;
    }

    public void setId_iva(int id_iva) {
        this.id_iva = id_iva;
    }

    public String getNombre_iva() {
        return nombre_iva;
    }

    public void setNombre_iva(String nombre_iva) {
        this.nombre_iva = nombre_iva;
    }

    public int getPorcentaje_iva() {
        return porcentaje_iva;
    }

    public void setPorcentaje_iva(int porcentaje_iva) {
        this.porcentaje_iva = porcentaje_iva;
    }

    
}
