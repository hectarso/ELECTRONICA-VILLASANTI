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
public class Ajustes {

    private int id_ajuste_stock;
    private Date fecha_ajuste_stock;
     private String motivo_ajuste_stock;
    private Usuarios usuario;
   

    public Ajustes() {
    }

    public Ajustes(int id_ajuste_stock, Date fecha_ajuste_stock, String motivo_ajuste_stock, Usuarios usuario) {
        this.id_ajuste_stock = id_ajuste_stock;
        this.fecha_ajuste_stock = fecha_ajuste_stock;
        this.motivo_ajuste_stock = motivo_ajuste_stock;
        this.usuario = usuario;
    }

    public int getId_ajuste_stock() {
        return id_ajuste_stock;
    }

    public void setId_ajuste_stock(int id_ajuste_stock) {
        this.id_ajuste_stock = id_ajuste_stock;
    }

    public Date getFecha_ajuste_stock() {
        return fecha_ajuste_stock;
    }

    public void setFecha_ajuste_stock(Date fecha_ajuste_stock) {
        this.fecha_ajuste_stock = fecha_ajuste_stock;
    }

    public String getMotivo_ajuste_stock() {
        return motivo_ajuste_stock;
    }

    public void setMotivo_ajuste_stock(String motivo_ajuste_stock) {
        this.motivo_ajuste_stock = motivo_ajuste_stock;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    


   

}
