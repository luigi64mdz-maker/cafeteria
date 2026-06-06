package Formularios;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author emili
 */
public class MenuConsulta extends javax.swing.JPanel {

    private Image fondo;
    
 public MenuConsulta() {

    java.net.URL url =
            getClass().getResource("/imagenes/MenuConsulta.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró MenuConsulta.png"
        );
    }

    initComponents();

    setPreferredSize(
            new java.awt.Dimension(1000, 600)
    );

    // transparentar botones
    jButton1.setContentAreaFilled(false);
    jButton2.setContentAreaFilled(false);

    jButton1.setBorderPainted(false);
    jButton2.setBorderPainted(false);

    jButton1.setForeground(java.awt.Color.WHITE);
    jButton2.setForeground(java.awt.Color.WHITE);

    jLabel1.setForeground(java.awt.Color.WHITE);

    setOpaque(false);
}
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


  

    setPreferredSize(new java.awt.Dimension(1000, 600)); // 🔥 importante
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("CONSULTOR");

        jButton1.setText("Productos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cerrar sesion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton1)
                        .addGap(72, 72, 72)
                        .addComponent(jButton2)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(145, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Formularios.Login login = new Formularios.Login();

        frame.setContentPane(login);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // 🔥 ajusta tamaño automáticamente
        frame.setLocationRelativeTo(null);
    }
   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Formularios.comprasdelconsultor panel = new Formularios.comprasdelconsultor();

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // 🔥 ajusta tamaño automático
        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
