package Formularios;

import conexion.Conexion;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Login extends javax.swing.JPanel {

public Login() {

    java.net.URL url = getClass().getResource("/imagenes/Login.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró Login.png"
        );
    }

    initComponents();

    // transparentar panel
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
}
   

private Image fondo;

   public void registrarBitacora(String usuario, String tipo) {
    try {
        Connection con = Conexion.conectar();

        String sql = "INSERT INTO bitacora (usuario, fecha, hora, tipo) VALUES (?, CURDATE(), CURTIME(), ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, usuario);
        ps.setString(2, tipo);

        ps.executeUpdate();

        System.out.println("Bitacora guardada");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error en bitacora: " + e);
    }
   }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Usuario:");

        jLabel2.setText("password:");

        jButton1.setText("ENTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REGISTRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Cafelui");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(41, 41, 41)
                                .addComponent(jButton2))
                            .addComponent(jTextField1)
                            .addComponent(jPasswordField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel3)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(41, 41, 41))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String user = jTextField1.getText();
    String pass = jPasswordField1.getText();

    try {
        Connection con = conexion.Conexion.conectar();

        String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            // ✅ LOGIN EXITOSO → GUARDAR BITÁCORA
            registrarBitacora(user, "Exitoso");

            String rol = rs.getString("rol");

            javax.swing.JFrame v = new javax.swing.JFrame();

            if (rol.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(this, "Bienvenido ADMIN");
                v.setContentPane(new MenuAdmin());
            } else {
                JOptionPane.showMessageDialog(this, "Bienvenido CONSULTOR");
                v.setContentPane(new MenuConsulta());
            }

            v.setSize(400,300);
            v.setLocationRelativeTo(null);
            v.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            v.setVisible(true);

            javax.swing.JFrame actual = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            actual.dispose();

        } else {

            // ❌ LOGIN FALLIDO → GUARDAR BITÁCORA
            registrarBitacora(user, "Fallido");

            JOptionPane.showMessageDialog(this, "Datos incorrectos");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
javax.swing.JFrame v = new javax.swing.JFrame("Registro");
v.setSize(400,300);
v.add(new Registro());
v.setLocationRelativeTo(null);
v.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
