package Formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.BorderLayout;

import java.io.FileOutputStream;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class GraficaPrecios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GraficaPrecios.class.getName());

    private JFreeChart grafica;
    
    public GraficaPrecios() {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dibujarGrafica();
    }
    
    public void dibujarGrafica() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try {
        Connection con = conexion.Conexion.conectar(); 
        
        // Consultamos los productos ordenados del más barato al más caro
        String sql = "SELECT nombre, precio FROM productos ORDER BY precio ASC";
                   
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre"); 
            double precio = rs.getDouble("precio"); 
            
            
            dataset.setValue(precio, nombre, nombre);
        }
        
    } catch (Exception e) {
        System.out.println("Error al cargar precios: " + e.getMessage());
    }

    // Creamos la gráfica con orientación HORIZONTAL
   grafica = ChartFactory.createBarChart(
        "Catálogo de Productos por Precio", 
        "Productos",                           
        "Precio ($)",                 
        dataset,                            
        PlotOrientation.HORIZONTAL, // <-- Orientación horizontal para mejor lectura          
        false,                      // No necesitamos leyenda de colores ya que el nombre sale al lado
        true,                               
        false                               
    );

    // Colocamos la gráfica en tu panelGrafica
    ChartPanel chartPanel = new ChartPanel(grafica);
    panelGrafica.setLayout(new BorderLayout());
    panelGrafica.removeAll();
    panelGrafica.add(chartPanel, BorderLayout.CENTER);
    panelGrafica.revalidate();
    panelGrafica.repaint();
}
public void exportarGraficaAPDF() {
        if (grafica == null) {
            JOptionPane.showMessageDialog(this, "Primero debes generar la gráfica para poder exportarla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Gráfica como PDF");
        
        int seleccion = fileChooser.showSaveDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            java.io.File archivoGuardar = fileChooser.getSelectedFile();
            String ruta = archivoGuardar.getAbsolutePath();
            
            if (!ruta.toLowerCase().endsWith(".pdf")) {
                ruta += ".pdf";
            }
            
            try {
                Document documento = new Document(com.itextpdf.text.PageSize.LETTER.rotate());
                PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta));
                documento.open();
                
                int ancho = 700;
                int alto = 500;
                BufferedImage imagenGrafica = grafica.createBufferedImage(ancho, alto);
                
                com.itextpdf.text.Image imagenPDF = com.itextpdf.text.Image.getInstance(writer, imagenGrafica, 1.0f);
                imagenPDF.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                
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
            .addGap(0, 351, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        java.awt.EventQueue.invokeLater(() -> new GraficaPrecios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
