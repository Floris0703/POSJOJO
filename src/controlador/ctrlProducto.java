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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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

    public void listarProductos(JTable tabla) {

        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);

        model.addColumn("ID");
        model.addColumn("Descripción");
        model.addColumn("Stock");
        model.addColumn("Stock Min");
        model.addColumn("Costo");
        model.addColumn("Precio Venta");
        model.addColumn("Categoría");
        model.addColumn("Estado");

        String sql = "SELECT * FROM tb_Inventario where estado= true";

        try (Connection cn = Conexion.conectar();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("idItem");
                fila[1] = rs.getString("descripcion");
                fila[2] = rs.getInt("stock");
                fila[3] = rs.getInt("stock_min");
                fila[4] = rs.getDouble("costo");
                fila[5] = rs.getDouble("p_venta");
                fila[6] = rs.getString("categoria");
                fila[7] = rs.getBoolean("estado");

                model.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        }
    }

    public boolean editarProducto(Producto p) {
        boolean respuesta = false;
        String sql = "UPDATE tb_Inventario SET descripcion=?, stock=?, stock_min=?, costo=?, p_venta=?, categoria=?, estado=? WHERE idItem=?";

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getDescripcion());
            ps.setInt(2, p.getStock());
            ps.setInt(3, p.getStockMin());
            ps.setDouble(4, p.getCosto());
            ps.setDouble(5, p.getpVenta());
            ps.setString(6, p.getCategoria());
            ps.setBoolean(7, p.isEstado());
            ps.setInt(8, p.getIdItem());

            respuesta = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al editar: " + e);
        }
        return respuesta;
    }

    public boolean eliminarProducto(int idProducto) {
        boolean respuesta = false;
        String sql = "UPDATE tb_Inventario SET estado = false WHERE idItem = ?";
        /*delete from tbInventario where idItem=?*/

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            respuesta = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e);
        }
        return respuesta;
    }

    public void buscarProductos(JTable tabla, String texto) {

        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);

        model.addColumn("ID");
        model.addColumn("Descripción");
        model.addColumn("Stock");
        model.addColumn("Stock Min");
        model.addColumn("Costo");
        model.addColumn("Precio");
        model.addColumn("Categoría");
        model.addColumn("Estado");

        String sql = "SELECT * FROM tb_Inventario "
                + "WHERE descripcion LIKE ? OR categoria LIKE ?";

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("idItem"),
                    rs.getString("descripcion"),
                    rs.getInt("stock"),
                    rs.getInt("stock_min"),
                    rs.getDouble("costo"),
                    rs.getDouble("p_venta"),
                    rs.getString("categoria"),
                    rs.getBoolean("estado")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e);
        }
    }

}
