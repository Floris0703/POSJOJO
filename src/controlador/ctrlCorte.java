/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Corte;
import modelo.DetalleCorte;

/**
 *
 * @author kevin
 */
public class ctrlCorte {

    public void generarCorteCaja(java.util.Date fechaSpinner) {

        // 1. Obtener totales (UNA sola vez)
        Corte totales = obtenerTotales(fechaSpinner);

        // 2. Convertir fecha solo para el nombre del archivo / encabezado
        LocalDate fecha = fechaSpinner.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // 3. Generar PDF usando los mismos datos
        generarPDF(fecha, totales);
    }

    public Corte obtenerTotales(java.util.Date fechaSpinner) {

        Corte totales = new Corte();

        double efectivo = 0;
        double tarjeta = 0;
        double transferencia = 0;

        LocalDate fecha = fechaSpinner.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Timestamp inicio = Timestamp.valueOf(fecha.atStartOfDay());
        Timestamp fin = Timestamp.valueOf(fecha.atTime(23, 59, 59));

        String sql = "SELECT formaPago, SUM(total) AS total FROM tb_ventasCabecera WHERE hora BETWEEN ? AND ? GROUP  BY  formaPago";

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setTimestamp(1, inicio);
            ps.setTimestamp(2, fin);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String forma = rs.getString("formaPago").toLowerCase();
                double total = rs.getDouble("total");

                switch (forma) {
                    case "efectivo":
                        efectivo = total;
                        break;
                    case "tarjeta":
                        tarjeta = total;
                        break;
                    case "transferencia":
                        transferencia = total;
                        break;
                }
            }

            totales.setEfectivo(efectivo);
            totales.setTarjeta(tarjeta);
            totales.setTransferencia(transferencia);
            totales.setTotalGeneral(efectivo + tarjeta + transferencia);

        } catch (SQLException e) {
            System.out.println("Error corte de caja: " + e);
        }

        return totales;
    }

    private void generarPDF(LocalDate fecha, Corte totales) {

        try {
            Document doc = new Document();
            String ruta = System.getProperty("user.home")
                    + File.separator + "OneDrive"
                    + File.separator + "Escritorio"
                    + File.separator + "Corte_" + fecha + ".pdf";

            //C:\Users\kevin\OneDrive\Escritorio
            PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            JOptionPane.showMessageDialog(null, "Generando PDF en: " + ruta);

            doc.open();

            doc.add(new Paragraph("CORTE DE CAJA"));
            doc.add(new Paragraph("Fecha: " + fecha));
            doc.add(new Paragraph(" "));

            doc.add(new Paragraph("Efectivo: " + totales.getEfectivo()));
            doc.add(new Paragraph("Tarjeta: " + totales.getTarjeta()));
            doc.add(new Paragraph("Transferencia: " + totales.getTransferencia()));
            doc.add(new Paragraph("Total general: " + totales.getTotalGeneral()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("DETALLE DE VENTAS"));
            doc.add(new Paragraph(" "));
            /*
            
            
            List<DetalleCorte> detalle = obtenerDetalleVentas(
                    java.sql.Date.valueOf(fecha)
            );

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);

            tabla.addCell("Producto");
            tabla.addCell("Cantidad");
            tabla.addCell("Total");

            for (DetalleCorte d : detalle) {
                tabla.addCell(d.getDescripcion());
                tabla.addCell(String.valueOf(d.getCantidad()));
                tabla.addCell(String.valueOf(d.getTotal()));
            }

            doc.add(tabla);
             */
            doc.close();
            Desktop.getDesktop().open(new File(ruta));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error al generar PDF:\n" + e.getMessage());
        }
    }

    private List<DetalleCorte> obtenerDetalleVentas(Date fechaSpinner) {

        List<DetalleCorte> lista = new ArrayList<>();

        LocalDate fecha = fechaSpinner.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Timestamp inicio = Timestamp.valueOf(fecha.atStartOfDay());
        Timestamp fin = Timestamp.valueOf(fecha.atTime(23, 59, 59));

        String sql = "SELECT i.descripcion,"
                + " SUM(d.cantidad) AS cantidad,"
                + "SUM(d.cantidad * d.precioU) AS total"
                + "FROM tb_ventasDetalle d JOIN tb_ventasCabecera c ON d.idCabeceraVenta = c.idCabeceraVenta"
                + "JOIN tb_Inventario i ON d.idProducto = i.idItem"
                + "WHERE c.hora BETWEEN ? AND ? GROUP  BY i.descripcion ORDER BY i.descripcion";

        try (Connection cn = Conexion.conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setTimestamp(1, inicio);
            ps.setTimestamp(2, fin);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new DetalleCorte(
                        rs.getString("descripcion"),
                        rs.getInt("cantidad"),
                        rs.getDouble("total")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error detalle corte: " + e);
        }

        return lista;
    }

}
