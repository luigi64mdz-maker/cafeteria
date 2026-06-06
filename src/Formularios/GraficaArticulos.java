package Formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;

import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class GraficaArticulos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GraficaArticulos.class.getName());

    private JFreeChart grafica;
    
    public GraficaArticulos() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dibujarGrafica();
    }
public void dibujarGrafica() {
    // 1. Usamos un dataset especial para gráficas circulares
    DefaultPieDataset dataset = new DefaultPieDataset();

    try {
        Connection con = conexion.Conexion.conectar(); 
        
        // Sumamos la cantidad histórica vendida de cada producto
        String sql = "SELECT nombre_producto, SUM(cantidad) AS total_vendido "
                   + "FROM detalle_venta "
                   + "GROUP BY nombre_producto "
                   + "ORDER BY total_vendido DESC";
                   
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String producto = rs.getString("nombre_producto"); 
            int cantidad = rs.getInt("total_vendido"); 
            
            // Para el pastel solo necesitamos 2 datos: (Nombre de la rebanada, Valor/Tamaño)
            dataset.setValue(producto, cantidad);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar artículos más vendidos: " + e.getMessage());
    }

    // 2. Creamos la gráfica de pastel en 3D (Ojo al "createPieChart3D")
    grafica = ChartFactory.createPieChart3D(
        "Artículos Más Vendidos (Histórico)", // Título
        dataset,                              // Los datos que extrajimos
        true,                                 // Mostrar leyenda abajo con colores
        true,                                 // Tooltips activados al pasar el mouse
        false                                 // URLs falsos
    );

    // --- INICIO DE LOS EFECTOS VISUALES ---
    // Extraemos el "plot" (el dibujo del pastel) para modificarlo
    org.jfree.chart.plot.PiePlot3D plot = (org.jfree.chart.plot.PiePlot3D) grafica.getPlot();
    
    // Efecto A: Transparencia (El .7f significa 70% opaco, le da un toque de cristal)
    plot.setForegroundAlpha(0.7f); 
    
    // Efecto B: Etiquetas avanzadas con valores y porcentajes
    plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
        "{0}: {1} piezas ({2})" 
    ));
    
    // Efecto C: Fondo limpio color blanco
    grafica.setBackgroundPaint(java.awt.Color.WHITE);
    plot.setBackgroundPaint(java.awt.Color.WHITE);
    // --- FIN DE LOS EFECTOS VISUALES ---

    // 3. Metemos la gráfica en tu panel
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
            .addGap(0, 366, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
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
                .addContainerGap()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        java.awt.EventQueue.invokeLater(() -> new GraficaArticulos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
