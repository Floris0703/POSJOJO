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
public class Historial {
    /*
    idHistorial int (20) auto_increment primary key,
    idUsuario int (20) not null, -- relacion 
    accion varchar (200) not null,
    fecha datetime not null,
    */
    private int idHistorial;
    private int idUsuario;
    private String accion;
    private LocalDateTime fecha;
    
    public Historial(int idHistorial, int idUsuario, String accion, LocalDateTime fecha){
        this.idHistorial=0;
        this.idUsuario=0;
        this.accion="";
        this.fecha=null;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
}
