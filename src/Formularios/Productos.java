package Formularios;

import conexion.Conexion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

   
public final class Productos extends javax.swing.JPanel {

    byte[] imagenSeleccionada = null; 
   
    private Image fondo;
    public Productos() {
        initComponents();
        cargarProductos(); 
        diseñoUI();
        centrarTabla();
        java.net.URL url =
            getClass().getResource("/imagenes/Productos.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró Productos.png"
        );
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

    void centrarTabla() {
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
    centro.setHorizontalAlignment(JLabel.CENTER);

    for (int i = 0; i < jTable1.getColumnCount(); i++) {
        if (i != 4) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
    }
}
   
void diseñoUI() {

 
    this.setBackground(new java.awt.Color(245, 245, 245));

  
    jButton1.setBackground(new java.awt.Color(231, 76, 60)); // rojo
    jButton1.setForeground(java.awt.Color.WHITE);

    jButton2.setBackground(new java.awt.Color(46, 204, 113)); // verde
    jButton2.setForeground(java.awt.Color.WHITE);

    jButton3.setBackground(new java.awt.Color(52, 152, 219)); // azul
    jButton3.setForeground(java.awt.Color.WHITE);

    jButton1.setFocusPainted(false);
    jButton2.setFocusPainted(false);
    jButton3.setFocusPainted(false);

  
    jLabel1.setText("PRODUCTOS TAQUERÍA");
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
    jLabel1.setForeground(new java.awt.Color(44, 62, 80));

   
    jTable1.setRowHeight(80);
    jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14));
    jTable1.setBackground(new java.awt.Color(250, 250, 250));

    jTable1.setSelectionBackground(new java.awt.Color(255, 204, 153));
    jTable1.setSelectionForeground(java.awt.Color.BLACK);

    jTable1.setShowGrid(false);
    jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));

    // ========================
    // HEADER TABLA
    // ========================
    jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    jTable1.getTableHeader().setBackground(new java.awt.Color(30, 30, 30));
    jTable1.getTableHeader().setForeground(java.awt.Color.WHITE);

    // ========================
    // CENTRAR TEXTO
    // ========================
    javax.swing.table.DefaultTableCellRenderer centro = new javax.swing.table.DefaultTableCellRenderer();
    centro.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    for (int i = 0; i < jTable1.getColumnCount(); i++) {
        if (i != 4) { // columna imagen no
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
    }

   
    
}
 void cargarProductos() {

    try {
        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
        ResultSet rs = ps.executeQuery();

        DefaultTableModel modelo = new DefaultTableModel() {

            public Class getColumnClass(int column) {
                if (column == 4) return ImageIcon.class;
                return Object.class;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Imagen");

        while (rs.next()) {

           byte[] img = rs.getBytes("imagen");

ImageIcon icono = null;

if (img != null) {
    ImageIcon tmp = new ImageIcon(img);
    Image im = tmp.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    icono = new ImageIcon(im);
} else {
    icono = new ImageIcon(); // vacío para que no truene
} 
            modelo.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                icono 
            });
        }

        jTable1.setModel(modelo);
        jTable1.setRowHeight(80);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e);
    }
    int fila = jTable1.getSelectedRow();

DefaultTableModel modelo = new DefaultTableModel() {

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 4) return ImageIcon.class;
        return Object.class;
    }

};
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jLabel1.setText("PRODUCTOS CAFELUI");

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Producto:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Stock:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Imagen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Volver");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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
                                .addGap(386, 386, 386)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField3)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jButton1)
                                        .addGap(59, 59, 59)
                                        .addComponent(jButton2)
                                        .addGap(67, 67, 67)
                                        .addComponent(jButton3)
                                        .addGap(92, 92, 92)
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                        .addComponent(jButton5)))))
                        .addGap(73, 73, 73)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(0, 46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// 🔴 VALIDAR CAMPOS
    if (jTextField1.getText().isEmpty() ||
        jTextField2.getText().isEmpty() ||
        jTextField3.getText().isEmpty() ||
        imagenSeleccionada == null) {

        JOptionPane.showMessageDialog(this, "Completa todos los campos y selecciona una imagen");
        return;
    }

    try {
        Connection con = Conexion.conectar();

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO productos(nombre, precio, stock, imagen) VALUES (?,?,?,?)"
        );

        ps.setString(1, jTextField1.getText());
        ps.setDouble(2, Double.parseDouble(jTextField2.getText()));
        ps.setInt(3, Integer.parseInt(jTextField3.getText()));
        ps.setBytes(4, imagenSeleccionada); // 👈 AQUÍ VA LA IMAGEN REAL

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Producto guardado correctamente");

        // 🔄 RECARGAR TABLA
        cargarProductos();

        // 🧹 LIMPIAR CAMPOS
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        imagenSeleccionada = null;

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Precio o stock inválidos");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    JFileChooser file = new JFileChooser();
    int opcion = file.showOpenDialog(this); // 👈 usa this (NO null)

    if (opcion == JFileChooser.APPROVE_OPTION) {
        try {
            File archivo = file.getSelectedFile();
            FileInputStream fis = new FileInputStream(archivo);

            // 🔥 GUARDAR EN VARIABLE GLOBAL
            imagenSeleccionada = fis.readAllBytes();

            // ✅ MENSAJE
            JOptionPane.showMessageDialog(this, "Imagen cargada correctamente");

            // 🔍 DEBUG (opcional)
            System.out.println("Tamaño imagen: " + imagenSeleccionada.length);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar imagen: " + e);
        }
    }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 int fila = jTable1.getSelectedRow();

    if (fila >= 0) {

        int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());

        try {
            Connection con = Conexion.conectar();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?"
            );

            ps.setString(1, jTextField1.getText());
            ps.setDouble(2, Double.parseDouble(jTextField2.getText()));
            ps.setInt(3, Integer.parseInt(jTextField3.getText()));
            ps.setInt(4, id);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Actualizado");

       cargarProductos(); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    } else {
        JOptionPane.showMessageDialog(null, "Selecciona un producto");
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int fila = jTable1.getSelectedRow();

    if (fila >= 0) {

        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro que quieres eliminar este producto?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());

            try {
                Connection con = Conexion.conectar();

                PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id=?");
                ps.setInt(1, id);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Eliminado");

                cargarProductos();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e);
            }
        }

    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un producto");
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        Formularios.MenuAdmin admin = new Formularios.MenuAdmin();

        frame.setContentPane(admin);
        frame.revalidate();
        frame.repaint();

        frame.pack(); // 🔥 ajusta tamaño correctamente
        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     int fila = jTable1.getSelectedRow();

if (fila >= 0) {
    jTextField1.setText(jTable1.getValueAt(fila, 1).toString());
    jTextField2.setText(jTable1.getValueAt(fila, 2).toString());
    jTextField3.setText(jTable1.getValueAt(fila, 3).toString());
  }   // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
