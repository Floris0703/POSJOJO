/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class Conexion {

    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/SistemaVentas", "root", "Spooky07_07.");
            return cn;

        } catch (SQLException e) {
            System.out.println("Error en la conexión con la base de datos " + e);
        }
        return null;
    }
}
