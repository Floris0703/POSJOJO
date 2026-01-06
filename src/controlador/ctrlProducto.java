/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Producto;

/**
 *
 * @author kevin
 */
public class ctrlProducto {

    /*
    idItem int  auto_increment primary key,
    descripcion varchar (30) not null,
    stock int  not null,
    stock_min int  not null,
    costo decimal(10,2) not null,
    p_venta decimal (10,2) not null,
    categoria varchar (100) not null,
    estado boolean not null
    
     */
    public boolean guardar(Producto objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_Inventario "
                    + "(descripcion, stock, stock_min, costo, p_venta, categoria, estado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            consulta.setString(1, objeto.getDescripcion()); //descripcion
            consulta.setInt(2, objeto.getStock());
            consulta.setInt(3, objeto.getStockMin());
            consulta.setDouble(4, objeto.getCosto());
            consulta.setDouble(5, objeto.getpVenta());
            consulta.setString(6, objeto.getCategoria());
            consulta.setBoolean(7, objeto.isEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar " + e);
        }
        return respuesta;
    }

    public boolean existeProducto(String descripcion) {
        boolean respuesta = false;
        String sql = "SELECT 1 FROM tb_Inventario WHERE descripcion = ?";
        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            respuesta = rs.next();

        } catch (SQLException e) {
            System.out.println("Error al consultar " + e);
        }
        return respuesta;
    }

}
