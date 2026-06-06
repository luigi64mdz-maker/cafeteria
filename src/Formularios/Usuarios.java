package Formularios;

import conexion.Conexion;
import java.awt.Graphics;
import java.awt.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Usuarios extends javax.swing.JPanel{
       

    /**
     * Creates new form usuarios
     */
 private Image fondo;

 public Usuarios() {

    java.net.URL url =
            getClass().getResource("/imagenes/MenuUsuario.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró MenuUsuarios.png"
        );
    }

    initComponents();

    cargarUsuarios();

    // transparentar tabla
    jScrollPane1.setOpaque(false);

    jScrollPane1.getViewport().setOpaque(false);

    jTable1.setOpaque(false);

    jTable1.setBackground(
            new java.awt.Color(0,0,0,0)
    );

    jTable1.setForeground(java.awt.Color.WHITE);

    jTable1.setShowGrid(false);

    jTable1.getTableHeader().setOpaque(false);

    jTable1.getTableHeader().setBackground(
            new java.awt.Color(0,0,0,0)
    );

    jTable1.getTableHeader().setForeground(
            java.awt.Color.WHITE
    );

    // transparentar botones
    jButton1.setContentAreaFilled(false);
    jButton2.setContentAreaFilled(false);
    jButton3.setContentAreaFilled(false);
    jButton4.setContentAreaFilled(false);

    jButton1.setBorderPainted(false);
    jButton2.setBorderPainted(false);
    jButton3.setBorderPainted(false);
    jButton4.setBorderPainted(false);

    jButton1.setForeground(java.awt.Color.WHITE);
    jButton2.setForeground(java.awt.Color.WHITE);
    jButton3.setForeground(java.awt.Color.WHITE);
    jButton4.setForeground(java.awt.Color.WHITE);

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
}

       
    

   

    void cargarUsuarios() {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT id, usuario, correo, password, fechaderegistro, rol FROM usuarios";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("Usuario");
            modelo.addColumn("Correo");
            modelo.addColumn("Password");
            modelo.addColumn("Fecha");
            modelo.addColumn("Rol");

            while (rs.next()) {
                Object[] fila = new Object[6];

                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("usuario");
                fila[2] = rs.getString("correo");
                fila[3] = rs.getString("password");
                fila[4] = rs.getString("fechaderegistro");
                fila[5] = rs.getString("rol");

                modelo.addRow(fila);
            }

            jTable1.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("USUARIOS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButton1)
                .addGap(112, 112, 112)
                .addComponent(jButton2)
                .addGap(119, 119, 119)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(0, 87, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String user = JOptionPane.showInputDialog("Usuario:");
String correo = JOptionPane.showInputDialog("Correo:");
String pass = JOptionPane.showInputDialog("Password:");

try {
    Connection con = Conexion.conectar();

    String sql = "INSERT INTO usuarios (usuario, correo, password, rol) VALUES (?,?,?,'consultor')";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, user);
    ps.setString(2, correo);
    ps.setString(3, pass);

    ps.executeUpdate();

    JOptionPane.showMessageDialog(this, "Usuario agregado");

    cargarUsuarios();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error: " + e);
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int fila = jTable1.getSelectedRow();

if (fila >= 0) {

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "¿Estás seguro de eliminar este usuario?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {

        int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());

        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM usuarios WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Usuario eliminado");

            cargarUsuarios();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }

} else {
    JOptionPane.showMessageDialog(this, "Selecciona un usuario");
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     int fila = jTable1.getSelectedRow();

if (fila >= 0) {

    String userActual = jTable1.getValueAt(fila, 0).toString();

    String nuevoUser = JOptionPane.showInputDialog("Nuevo usuario:");
    String nuevoGmail = JOptionPane.showInputDialog("Nuevo Gmail:");
    String nuevaPass = JOptionPane.showInputDialog("Nueva Password:");

    try {
        Connection con = Conexion.conectar();

        String sql = "UPDATE usuarios SET usuario=?, gmail=?, password=? WHERE usuario=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, nuevoUser);
        ps.setString(2, nuevoGmail);
        ps.setString(3, nuevaPass);
        ps.setString(4, userActual);

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Usuario actualizado");

        cargarUsuarios();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e);
    }

} else {
    JOptionPane.showMessageDialog(this, "Selecciona un usuario");
}
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
javax.swing.JFrame v = new javax.swing.JFrame("Admin");
        v.setContentPane(new MenuAdmin());
    v.setSize(400,300);
    v.setLocationRelativeTo(null);
    v.setVisible(true);

    javax.swing.JFrame actual = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
    actual.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
