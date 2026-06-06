package Formularios;

import conexion.Conexion;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Graphics;
import java.awt.Image;

public class Bitacora extends JPanel {
    private Image fondo;


   private JTable tabla;
    


   public Bitacora() {

    java.net.URL url =
            getClass().getResource("/imagenes/Bitacora.png");

    if (url != null) {

        fondo = new javax.swing.ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró Bitacora.png"
        );
    }

    

    initComponents();

    tabla = jTable1;

    cargarBitacora();

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

    // botones transparentes
    jButton1.setContentAreaFilled(false);
    jButton2.setContentAreaFilled(false);

    jButton1.setBorderPainted(false);
    jButton2.setBorderPainted(false);

    jButton1.setForeground(java.awt.Color.WHITE);
    jButton2.setForeground(java.awt.Color.WHITE);

    jLabel1.setForeground(java.awt.Color.WHITE);

    setOpaque(false);
    

    tabla = jTable1; //
    cargarBitacora();

   
}
 

    private void cargarBitacora() {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Tipo");

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT usuario, fecha, hora, tipo FROM bitacora ORDER BY id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];

                fila[0] = rs.getString("usuario");
                fila[1] = rs.getDate("fecha");
                fila[2] = rs.getTime("hora");
                fila[3] = rs.getString("tipo");

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar bitácora: " + e);
        }
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("BITACORA");

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

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ELIMINAR");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(385, 385, 385)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addComponent(jButton1)
                                .addGap(199, 199, 199)
                                .addComponent(jButton2)))
                        .addGap(0, 200, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        // 🔥 IMPORTANTE: usa el nombre EXACTO de tu clase
        Formularios.MenuAdmin panel = new Formularios.MenuAdmin();

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // ajusta tamaño
        frame.setLocationRelativeTo(null);
    }   // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
int fila = tabla.getSelectedRow();

    if (fila >= 0) {

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de eliminar este registro?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            String usuario = tabla.getValueAt(fila, 0).toString();
            String fecha = tabla.getValueAt(fila, 1).toString();
            String hora = tabla.getValueAt(fila, 2).toString();

            try {
                Connection con = Conexion.conectar();

                String sql = "DELETE FROM bitacora WHERE usuario=? AND fecha=? AND hora=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, usuario);
                ps.setString(2, fecha);
                ps.setString(3, hora);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registro eliminado");

                cargarBitacora(); // recargar tabla

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e);
            }
        }

    }else {
        JOptionPane.showMessageDialog(this, "Selecciona un registro");
    }   // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
