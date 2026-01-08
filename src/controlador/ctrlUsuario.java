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
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author kevin
 */
public class ctrlUsuario {

    //metodo para iniciar sesion
    public int LoginUser(Usuario objeto) {

        int idUsuario = 0;
        Connection cn = Conexion.conectar();

        String sql = "SELECT idUsuario FROM tb_Usuario WHERE nombre =  ? AND  pssword = ? ";

    try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPsswrd());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
            }

        } catch (SQLException e) {
            System.out.println("Error login " + e);
        }

        return idUsuario;
    }
}
