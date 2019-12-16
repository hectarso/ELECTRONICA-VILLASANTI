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
public class Timbrados {
    private int id_timbrado;
    private String numero_timbrado;
    private Date fechaini_timbrado;
    private Date fechaact_timbrado;
    private Date fechafin_timbrado;
    private int desde_timbrado;
    private int hasta_timbrado;
    private String estado_timbrado;
    private Establecimientos establecimiento;

    public Timbrados() {
    }

    public Timbrados(int id_timbrado, String numero_timbrado, Date fechaini_timbrado, Date fechaact_timbrado, Date fechafin_timbrado, int desde_timbrado, int hasta_timbrado, String estado_timbrado, Establecimientos establecimiento) {
        this.id_timbrado = id_timbrado;
        this.numero_timbrado = numero_timbrado;
        this.fechaini_timbrado = fechaini_timbrado;
        this.fechaact_timbrado = fechaact_timbrado;
        this.fechafin_timbrado = fechafin_timbrado;
        this.desde_timbrado = desde_timbrado;
        this.hasta_timbrado = hasta_timbrado;
        this.estado_timbrado = estado_timbrado;
        this.establecimiento = establecimiento;
    }

    public int getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(int id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFechaini_timbrado() {
        return fechaini_timbrado;
    }

    public void setFechaini_timbrado(Date fechaini_timbrado) {
        this.fechaini_timbrado = fechaini_timbrado;
    }

    public Date getFechaact_timbrado() {
        return fechaact_timbrado;
    }

    public void setFechaact_timbrado(Date fechaact_timbrado) {
        this.fechaact_timbrado = fechaact_timbrado;
    }

    public Date getFechafin_timbrado() {
        return fechafin_timbrado;
    }

    public void setFechafin_timbrado(Date fechafin_timbrado) {
        this.fechafin_timbrado = fechafin_timbrado;
    }

    public int getDesde_timbrado() {
        return desde_timbrado;
    }

    public void setDesde_timbrado(int desde_timbrado) {
        this.desde_timbrado = desde_timbrado;
    }

    public int getHasta_timbrado() {
        return hasta_timbrado;
    }

    public void setHasta_timbrado(int hasta_timbrado) {
        this.hasta_timbrado = hasta_timbrado;
    }

    public String getEstado_timbrado() {
        return estado_timbrado;
    }

    public void setEstado_timbrado(String estado_timbrado) {
        this.estado_timbrado = estado_timbrado;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

    
}
