package Formularios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TicketPDF {

    public static String generarTicket(ArrayList<Object[]> carrito) {

        try {

            String fechaArchivo = new SimpleDateFormat(
                    "dd-MM-yyyy_HH-mm-ss"
            ).format(new Date());

            String tempDir = System.getProperty("java.io.tmpdir");
            java.io.File archivoTemp = new java.io.File(tempDir, "Ticket_Cafelui_" + fechaArchivo + ".pdf");
            String ruta = archivoTemp.getAbsolutePath();

            Document documento = new Document();

            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(ruta)
            );

            documento.open();

            Font titulo = new Font(Font.FontFamily.HELVETICA, 26, Font.BOLD, BaseColor.BLACK);
            Font normal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
            Font encabezado = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.WHITE);
            Font totalFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);

            Paragraph nombreNegocio = new Paragraph("CAFELUI", titulo);
            nombreNegocio.setAlignment(Element.ALIGN_CENTER);
            documento.add(nombreNegocio);

            Paragraph subtitulo = new Paragraph("Ticket de compra", normal);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);

            Paragraph direccion = new Paragraph("Dirección: Av. Cafelui #123, México", normal);
            direccion.setAlignment(Element.ALIGN_CENTER);
            documento.add(direccion);

            String fechaActual = new SimpleDateFormat(
                    "dd/MM/yyyy HH:mm:ss"
            ).format(new Date());

            Paragraph fecha = new Paragraph("Fecha y hora: " + fechaActual, normal);
            fecha.setAlignment(Element.ALIGN_CENTER);
            documento.add(fecha);

            documento.add(new Paragraph("\n"));

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1, 2, 3, 2, 2, 2});

            agregarEncabezado(tabla, "ID", encabezado);
            agregarEncabezado(tabla, "Imagen", encabezado);
            agregarEncabezado(tabla, "Producto", encabezado);
            agregarEncabezado(tabla, "Precio", encabezado);
            agregarEncabezado(tabla, "Cantidad", encabezado);
            agregarEncabezado(tabla, "Subtotal", encabezado);

            double totalGeneral = 0;

            for (Object[] item : carrito) {

                int id = (int) item[0];
                String nombre = item[1].toString();
                double precio = (double) item[2];
                int cantidad = (int) item[3];
                ImageIcon icono = (ImageIcon) item[4];

                double subtotal = precio * cantidad;
                totalGeneral += subtotal;

                agregarCelda(tabla, String.valueOf(id));
                agregarImagen(tabla, icono);
                agregarCelda(tabla, nombre);
                agregarCelda(tabla, "$" + precio);
                agregarCelda(tabla, String.valueOf(cantidad));
                agregarCelda(tabla, "$" + subtotal);
            }

            documento.add(tabla);

            documento.add(new Paragraph("\n"));

            Paragraph total = new Paragraph("TOTAL: $" + totalGeneral, totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            documento.add(total);

            documento.add(new Paragraph("\n"));

            Paragraph gracias = new Paragraph("Gracias por comprar en Cafelui ☕", normal);
            gracias.setAlignment(Element.ALIGN_CENTER);
            documento.add(gracias);

            documento.close();

            JOptionPane.showMessageDialog(null, "Ticket generado correctamente");

            java.awt.Desktop.getDesktop().open(
                    new java.io.File(ruta)
            );
            return ruta;
        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al generar ticket: " + e
            );
            return null;
        }
    }

    private static void agregarEncabezado(
            PdfPTable tabla,
            String texto,
            Font fuente
    ) {

        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setBackgroundColor(new BaseColor(92, 51, 23));
        celda.setPadding(6);

        tabla.addCell(celda);
    }

    private static void agregarCelda(
            PdfPTable tabla,
            String texto
    ) {

        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);

        tabla.addCell(celda);
    }

    private static void agregarImagen(
            PdfPTable tabla,
            ImageIcon icono
    ) {

        try {

            Image imagenPDF = Image.getInstance(
                    icono.getImage(),
                    null
            );

            imagenPDF.scaleToFit(55, 55);

            PdfPCell celda = new PdfPCell(imagenPDF, true);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.setPadding(5);

            tabla.addCell(celda);

        } catch (Exception e) {

            PdfPCell celda = new PdfPCell(new Phrase("Sin imagen"));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tabla.addCell(celda);
        }
    }
}