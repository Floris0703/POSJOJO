/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
    public boolean LoginUser(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        String sql = "SELECT 1 FROM tb_Usuario WHERE nombre = ? AND pssword = ?";
        Statement st;
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getPsswrd());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion");
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
        }
        return respuesta;
    }

}
