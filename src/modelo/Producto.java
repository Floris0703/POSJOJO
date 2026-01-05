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
public class Producto {

    private int idItem;
    private String descripcion;
    private int stock;
    private int stockMin;
    private double costo;
    private double pVenta;
    private String categoria;
    private boolean estado;

    //Constructor
    public Producto(int idItem, String descripcion, int stock, int stockMin, double costo, double pVenta, String categoria, boolean estado) {
        this.idItem = 0;
        this.descripcion = "";
        this.stock = 0;
        this.stockMin = 0;
        this.costo = 0;
        this.pVenta = 0;
        this.categoria = "";
        this.estado = false;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getpVenta() {
        return pVenta;
    }

    public void setpVenta(double pVenta) {
        this.pVenta = pVenta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
