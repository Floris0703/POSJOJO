/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;
/**
 *
 * @author kevin
 */
public class CabeceraVentas {

    /*
    idCabeceraVenta int auto_increment primary key,
    total decimal (10,2) not null,
    formaPago varchar (15) not null,
    hora datetime not null,
    estado boolean not null,
    idUsuario int not null, -- relacion
     */
    private int idCabecera;
    private double total;
    private String formaPago;
    private LocalDateTime hora;
    private boolean estado;
    private int idUsuario;

    public CabeceraVentas() {
        this.idCabecera = 0;
        this.total = 0.0;
        this.formaPago = "";
        this.hora = null;
        this.estado = false;
        this.idUsuario = 0; //foreign key
    }

    public int getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(int idCabecera) {
        this.idCabecera = idCabecera;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
