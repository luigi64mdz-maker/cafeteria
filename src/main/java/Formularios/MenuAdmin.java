package Formularios;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Formularios.Bitacora;


public class MenuAdmin extends JPanel {

   

  
public MenuAdmin() {

    java.net.URL url = getClass().getResource("/imagenes/MenuAdmin.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró MenuAdmin.png"
        );
    }

    initComponents();

    setOpaque(false);
}
    private Image fondo;
    
    @Override
protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    if (fondo != null) {

        g.drawImage(
                fondo,
                0,
                0,
                getWidth(),
                getHeight(),
                this
        );
    }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        comboReportes = new javax.swing.JComboBox<>();

        jButton4.setText("jButton4");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("ADMINISTRADOR");

        jButton1.setText("Usuarios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Productos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Bitacora");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Cerrar sesion");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Generar grafica");
        jButton6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jButton6AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        comboReportes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ventas por Día", " ", "Usuarios", " ", "Artículos más vendidos", " ", "Catálogo por Precios", " ", "Ingresos Totales", " ", "Stock bajo", " ", "Calificación de productos" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(comboReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(58, 58, 58))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Formularios.Productos p = new Formularios.Productos();

        frame.setContentPane(p);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // 🔥 ajusta tamaño automáticamente
        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Formularios.Usuarios u = new Formularios.Usuarios();

        frame.setContentPane(u);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // 🔥 ajusta tamaño automático
        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
javax.swing.JFrame ventana = new javax.swing.JFrame("Bitácora");

    ventana.setSize(600, 400);
    ventana.setLocationRelativeTo(null);
    ventana.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

    ventana.add(new Bitacora()); 

    ventana.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// Abrir login
    java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Login login = new Login();

        frame.setContentPane(login);

        frame.revalidate();
        frame.repaint();

        // 🔥 ESTA LÍNEA ES LA CLAVE
        frame.pack(); 

        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
   
    String seleccion = comboReportes.getSelectedItem().toString();

    switch (seleccion) {
        case "Ventas por Día":
            
            GraficaVentas gVentas = new GraficaVentas();
            gVentas.setVisible(true);
            break;
            
        case "Usuarios":
            GraficaUsuarios gUsuarios = new GraficaUsuarios();
            gUsuarios.setVisible(true);
            break;
            
       case "Artículos más vendidos":
            GraficaArticulos gArticulos = new GraficaArticulos();
            gArticulos.setVisible(true);
            break;
            
        case "Catálogo por Precios":
            GraficaPrecios gPrecios = new GraficaPrecios();
            gPrecios.setVisible(true);
            break;
            
        case "Ingresos Totales":
            GraficaIngresos gIngresos = new GraficaIngresos();
            gIngresos.setVisible(true);
            break;
            
        case "Stock bajo":
            GraficaStock gStock = new GraficaStock();
            gStock.setVisible(true);
            break;
            
        case "Calificación de productos":
            GraficaCalificaciones gCalificaciones = new GraficaCalificaciones();
            gCalificaciones.setVisible(true);
            break;
            
        default:
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un reporte válido.");
            break;
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jButton6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6AncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboReportes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
