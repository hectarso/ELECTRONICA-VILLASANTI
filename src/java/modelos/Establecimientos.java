/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author ALUMNO1
 */
public class Establecimientos {

    private int id_establecimiento;
    private String nombre_establecimiento;
    private String ruc_establecimiento;
    private String direccion_establecimiento;
    private Ciudades ciudad;
    private String telefono_establecimiento;
    private String representante_establecimiento;

    public Establecimientos() {
    }

    public Establecimientos(int id_establecimiento, String nombre_establecimiento, String ruc_establecimiento, String direccion_establecimiento, Ciudades ciudad, String telefono_establecimiento, String representante_establecimiento) {
        this.id_establecimiento = id_establecimiento;
        this.nombre_establecimiento = nombre_establecimiento;
        this.ruc_establecimiento = ruc_establecimiento;
        this.direccion_establecimiento = direccion_establecimiento;
        this.ciudad = ciudad;
        this.telefono_establecimiento = telefono_establecimiento;
        this.representante_establecimiento = representante_establecimiento;
    }

    public int getId_establecimiento() {
        return id_establecimiento;
    }

    public void setId_establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public String getNombre_establecimiento() {
        return nombre_establecimiento;
    }

    public void setNombre_establecimiento(String nombre_establecimiento) {
        this.nombre_establecimiento = nombre_establecimiento;
    }

    public String getRuc_establecimiento() {
        return ruc_establecimiento;
    }

    public void setRuc_establecimiento(String ruc_establecimiento) {
        this.ruc_establecimiento = ruc_establecimiento;
    }

    public String getDireccion_establecimiento() {
        return direccion_establecimiento;
    }

    public void setDireccion_establecimiento(String direccion_establecimiento) {
        this.direccion_establecimiento = direccion_establecimiento;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono_establecimiento() {
        return telefono_establecimiento;
    }

    public void setTelefono_establecimiento(String telefono_establecimiento) {
        this.telefono_establecimiento = telefono_establecimiento;
    }

    public String getRepresentante_establecimiento() {
        return representante_establecimiento;
    }

    public void setRepresentante_establecimiento(String representante_establecimiento) {
        this.representante_establecimiento = representante_establecimiento;
    }

    
}
