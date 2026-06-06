package Formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.BasicStroke;

import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class GraficaUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GraficaUsuarios.class.getName());

    private JFreeChart grafica;
    
    public GraficaUsuarios() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dibujarGrafica();
    }

    public void dibujarGrafica() {
    DefaultPieDataset dataset = new DefaultPieDataset();

    try {
        Connection con = conexion.Conexion.conectar(); 
        
        // Contamos cuántos registros tiene cada usuario en la bitácora
        String sql = "SELECT usuario, COUNT(*) AS total_actividad "
                   + "FROM bitacora "
                   + "GROUP BY usuario "
                   + "ORDER BY total_actividad DESC";
                   
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombreUsuario = rs.getString("usuario"); 
            int actividad = rs.getInt("total_actividad"); 
            
            dataset.setValue(nombreUsuario, actividad);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar usuarios: " + e.getMessage());
    }

    // 1. Creamos la gráfica tipo ANILLO (Ring Chart)
    grafica = ChartFactory.createRingChart(
        "Nivel de Actividad por Usuario", 
        dataset,                              
        true,                                 
        true,                                 
        false                                 
    );

    // 2. Personalizamos el diseño del anillo para que se vea increíble
    RingPlot plot = (RingPlot) grafica.getPlot();
    
    // Fondo blanco limpio
    grafica.setBackgroundPaint(Color.WHITE);
    plot.setBackgroundPaint(Color.WHITE);
    
    // Ajustamos el grosor del anillo (0.30 es más delgado y elegante)
    plot.setSectionDepth(0.30); 
    
    // Activamos las líneas separadoras entre las rebanadas
    plot.setSeparatorsVisible(true);
    plot.setSeparatorStroke(new BasicStroke(3.0f)); // Grosor de la separación
    plot.setSeparatorPaint(Color.WHITE);            // Color de la separación
    
    // Etiquetas limpias
    plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
        "{0}: {1} acciones" 
    ));

    // 3. Inyectamos la gráfica al panel
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
            .addGap(0, 365, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
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
                .addGap(0, 35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        java.awt.EventQueue.invokeLater(() -> new GraficaUsuarios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
