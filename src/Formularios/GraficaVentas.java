package Formularios;

 import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;

import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class GraficaVentas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GraficaVentas.class.getName());

    private JFreeChart grafica;
    
    public GraficaVentas() {
        initComponents();
        dibujarGrafica();
    }
   
public void dibujarGrafica() {
    // 1. Le preguntamos al usuario la fecha
    String fechaBuscada = javax.swing.JOptionPane.showInputDialog(this, 
        "Ingresa la fecha a consultar (Formato AAAA-MM-DD, ej. 2026-05-24):", 
        "Buscar Ventas por Día", 
        javax.swing.JOptionPane.QUESTION_MESSAGE);

    // Si el administrador presiona "Cancelar" o deja en blanco, cerramos la ventana y no hacemos nada
    if (fechaBuscada == null || fechaBuscada.trim().isEmpty()) {
        this.dispose(); // Cierra la ventana actual
        return; 
    }

    // 2. Creamos el conjunto de datos
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    boolean hayDatos = false; // Nos servirá para saber si hubo ventas ese día

    // 3. Conexión y consulta filtrada
    try {
        Connection con = conexion.Conexion.conectar(); 
        
        // ¡OJO AL WHERE! Aquí le decimos a MySQL que solo traiga lo de la fecha buscada
        String sql = "SELECT v.fecha, d.nombre_producto, SUM(d.cantidad) AS total_vendido "
                   + "FROM detalle_venta d "
                   + "INNER JOIN ventas v ON d.venta_id = v.id "
                   + "WHERE v.fecha = ? " // <--- FILTRO DE FECHA
                   + "GROUP BY v.fecha, d.nombre_producto "
                   + "ORDER BY total_vendido DESC";
                   
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, fechaBuscada); // Insertamos la fecha que escribió el usuario en el signo de interrogación
        
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            hayDatos = true; // Si entra aquí, es que sí encontró ventas
            String fecha = rs.getString("fecha"); 
            String producto = rs.getString("nombre_producto"); 
            int cantidad = rs.getInt("total_vendido"); 
            
            dataset.setValue(cantidad, producto, fecha);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar datos para la gráfica: " + e.getMessage());
    }

    // Si no hubo ventas, le avisamos al administrador
    if (!hayDatos) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "No se encontraron ventas registradas para el día: " + fechaBuscada, 
            "Sin resultados", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    // 4. Creamos la gráfica con el título personalizado
   grafica = ChartFactory.createBarChart(
        "Ventas del día: " + fechaBuscada,  // El título ahora muestra el día consultado
        "Fecha",                            
        "Cantidad Vendida",                 
        dataset,                            
        PlotOrientation.VERTICAL,           
        true,                               
        true,                               
        false                               
    );

    // 5. Dibujamos la gráfica en el panel
    ChartPanel chartPanel = new ChartPanel(grafica);
    panelGrafica.setLayout(new java.awt.BorderLayout());
    panelGrafica.removeAll();
    panelGrafica.add(chartPanel, java.awt.BorderLayout.CENTER);
    panelGrafica.revalidate();
    panelGrafica.repaint();
}

public void exportarGraficaAPDF() {
        // 1. Verificamos que la gráfica ya exista
        if (grafica == null) {
            JOptionPane.showMessageDialog(this, "Primero debes generar la gráfica para poder exportarla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Abrimos la ventana para que el usuario elija dónde guardar el PDF
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Gráfica como PDF");
        
        int seleccion = fileChooser.showSaveDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivoGuardar = fileChooser.getSelectedFile();
            String ruta = archivoGuardar.getAbsolutePath();
            
            // Nos aseguramos de que el archivo termine con la extensión .pdf
            if (!ruta.toLowerCase().endsWith(".pdf")) {
                ruta += ".pdf";
            }
            
            try {
                // 3. Creamos el documento PDF (Formato horizontal / Carta)
                Document documento = new Document(com.itextpdf.text.PageSize.LETTER.rotate());
                PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                documento.open();
                
                // 4. Tomamos una "foto" de la gráfica de JFreeChart (Resolución: 700x500)
                int ancho = 700;
                int alto = 500;
                BufferedImage imagenGrafica = grafica.createBufferedImage(ancho, alto);
                
                // 5. Convertimos esa foto al formato que entiende iTextPDF
                com.itextpdf.text.Image imagenPDF = com.itextpdf.text.Image.getInstance(writer, imagenGrafica, 1.0f);
                imagenPDF.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                
                // 6. Pegamos la imagen en el PDF y cerramos
                documento.add(imagenPDF);
                documento.close();
                
                JOptionPane.showMessageDialog(this, "¡PDF generado y guardado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al crear el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGrafica = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        jButton1.setText("Exportar a PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    exportarGraficaAPDF();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GraficaVentas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
