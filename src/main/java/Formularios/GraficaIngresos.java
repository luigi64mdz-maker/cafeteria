package Formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.BasicStroke;

import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class GraficaIngresos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GraficaIngresos.class.getName());

    private JFreeChart grafica;
    
    public GraficaIngresos() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    dibujarGrafica();
    }

    public void dibujarGrafica() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try {
        Connection con = conexion.Conexion.conectar(); 
        
        String sql = "SELECT fecha, SUM(total) as diario FROM ventas "
                   + "GROUP BY fecha ORDER BY fecha ASC";
                   
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String fecha = rs.getString("fecha"); 
            double ingresoDiario = rs.getDouble("diario"); 
            dataset.addValue(ingresoDiario, "Ingresos", fecha);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar ingresos: " + e.getMessage());
    }

    // Creamos la gráfica base (Ocultamos la leyenda de abajo porque es redundante)
   grafica = ChartFactory.createLineChart(
        "Histórico de Ingresos Diarios", 
        "Fecha", "Monto Total ($)",                 
        dataset, PlotOrientation.VERTICAL,           
        false, true, false                               
    );

    // --- DISEÑO PREMIUM (ESTILO APP FINANCIERA) ---
    grafica.setBackgroundPaint(Color.WHITE);
    org.jfree.chart.plot.CategoryPlot plot = grafica.getCategoryPlot();
    
    // Fondo gris ultra claro para resaltar la línea
    plot.setBackgroundPaint(new Color(248, 249, 250));
    
    // Cuadrícula punteada elegante
    plot.setRangeGridlinePaint(new Color(180, 180, 180));
    plot.setRangeGridlineStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{5.0f}, 0.0f));

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    
    // Línea más gruesa y color verde esmeralda vibrante
    renderer.setSeriesStroke(0, new BasicStroke(4.0f));
    renderer.setSeriesPaint(0, new Color(39, 174, 96)); 

    // Puntos redondos modernos (borde verde, centro blanco)
    renderer.setDefaultShapesVisible(true);
    renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-6.0, -6.0, 12.0, 12.0));
    renderer.setSeriesFillPaint(0, Color.WHITE);
    renderer.setUseFillPaint(true);

    // Mostrar las cantidades exactas ($) arriba de los puntos
    renderer.setSeriesItemLabelsVisible(0, true);
    renderer.setSeriesItemLabelGenerator(0, new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
        "${2}", java.text.NumberFormat.getInstance()
    ));
    renderer.setSeriesItemLabelFont(0, new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
    
    plot.setRenderer(renderer);
    // --- FIN DEL DISEÑO PREMIUM ---

    ChartPanel chartPanel = new ChartPanel(grafica);
    panelGrafica.setLayout(new BorderLayout());
    panelGrafica.removeAll();
    panelGrafica.add(chartPanel, BorderLayout.CENTER);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGrafica = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(8, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(() -> new GraficaIngresos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
