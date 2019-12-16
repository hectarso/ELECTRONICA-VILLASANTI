/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO
 */
public class Cajas {
    private int id_caja;
    private Date fecha_apertura;
    private int monto_inicial;
    private String estado_caja;
    private Usuarios usuario;

    public Cajas() {
    }

    public Cajas(int id_caja, Date fecha_apertura, int monto_inicial, String estado_caja, Usuarios usuario) {
        this.id_caja = id_caja;
        this.fecha_apertura = fecha_apertura;
        this.monto_inicial = monto_inicial;
        this.estado_caja = estado_caja;
        this.usuario = usuario;
    }

    public int getId_caja() {
        return id_caja;
    }

    public void setId_caja(int id_caja) {
        this.id_caja = id_caja;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public int getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(int monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public String getEstado_caja() {
        return estado_caja;
    }

    public void setEstado_caja(String estado_caja) {
        this.estado_caja = estado_caja;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
    
}
