/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author kevin
 */
public class DetalleVentas {
    /*
    idDetalleVenta int  auto_increment primary key,
    idCabeceraVenta int not null, -- relacion
    idProducto int  not null, -- relacion
    precioU decimal (10,2) not null,
    cantidad int  not null,
    */
    private int idDetalleVenta;
    private int idCabeceraVenta;
    private int idProducto;
    private double precioU;
    private int cantidad;
    
    public DetalleVentas(){
        this.idDetalleVenta=0;
        this.idCabeceraVenta=0;
        this.idProducto=0;
        this.precioU=0;
        this.cantidad=0;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
