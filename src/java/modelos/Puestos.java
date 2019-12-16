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
public class Puestos {
    private int id_puesto;
    private String nombre_puesto;
    private Establecimientos establecimiento;

    public Puestos() {
    }

    public Puestos(int id_puesto, String nombre_puesto, Establecimientos establecimiento) {
        this.id_puesto = id_puesto;
        this.nombre_puesto = nombre_puesto;
        this.establecimiento = establecimiento;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre_puesto() {
        return nombre_puesto;
    }

    public void setNombre_puesto(String nombre_puesto) {
        this.nombre_puesto = nombre_puesto;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

   
}
