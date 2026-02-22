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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    
    public boolean guardarUsuario(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            PreparedStatement consulta = cn.prepareStatement("INSERT INTO tb_Usuario "
                    + "(nombre, pssword, rol) "
                    + "VALUES (?, ?, ?)");
            consulta.setString(1, objeto.getNombre()); //descripcion
            consulta.setString(2, objeto.getPsswrd());
            consulta.setString(3, objeto.getRol());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar " + e);
        }
        return respuesta;
    }
    
    public boolean existeUsuario(String descripcion) {
        boolean respuesta = false;
        String sql = "SELECT 1 FROM tb_Usuario WHERE nombre = ?";
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

    
    public void listarUsuarios(JTable tabla) {

        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);

        model.addColumn("Nombre");
        model.addColumn("Contraseña");
        model.addColumn("Rol");
        String sql = "SELECT * FROM tb_Usuario";

        try (Connection cn = Conexion.conectar();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("idUsuario");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("pssword");
                fila[3] = rs.getString("rol");
                model.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        }
    }
    
    public boolean eliminarUsuario(int idUsuario) {
        boolean respuesta = false;
        String sql = "delete from tb_Usuario where idUsuario = ?";
        /*delete from tbInventario where idItem=?*/

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            respuesta = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e);
        }
        return respuesta;
    }

}
