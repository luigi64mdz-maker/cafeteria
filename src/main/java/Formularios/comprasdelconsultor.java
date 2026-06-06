package Formularios;

import conexion.Conexion;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class comprasdelconsultor extends javax.swing.JPanel {
 private Image fondo;
 ArrayList<Object[]> carrito = new ArrayList<>();
    public comprasdelconsultor() {

        // cargar imagen
        fondo = new ImageIcon(
                getClass().getResource("/imagenes/fondo.png")
        ).getImage();

        initComponents();

        // transparente para ver fondo
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        jTable1.setOpaque(false);

        cargarProductos();
        centrarTabla();
    }

    // ===== PINTAR FONDO =====
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // imagen en toda la pantalla
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
    
   
    

    void centrarTabla() {

        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < jTable1.getColumnCount(); i++) {

            // NO tocar columna imagen
            if (i != 4) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centro);
            }
        }
    }

    void actualizarStock(int id, int nuevoStock) {

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE productos SET stock=? WHERE id=?"
            );

            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error stock: " + e);
        }
    }

    void cargarProductos() {

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");

            ResultSet rs = ps.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {

                    return column == 6 || column == 7;
                }

                @Override
                public Class<?> getColumnClass(int column) {

                    if (column == 4) {
                        return ImageIcon.class;
                    }

                    return Object.class;
                }
            };

            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            modelo.addColumn("Stock");
            modelo.addColumn("Imagen");
            modelo.addColumn("Cantidad");
            modelo.addColumn("+");
            modelo.addColumn("-");

            while (rs.next()) {

                byte[] img = rs.getBytes("imagen");

                ImageIcon icono;

                if (img != null) {

                    ImageIcon tmp = new ImageIcon(img);

                    Image im = tmp.getImage().getScaledInstance(
                        80,
                        80,
                        Image.SCALE_SMOOTH
                    );

                    icono = new ImageIcon(im);

                } else {

                    icono = new ImageIcon();
                }

                modelo.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    icono,
                    0,
                    "+",
                    "-"
                });
            }

            jTable1.setModel(modelo);

            jTable1.setRowHeight(80);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error: " + e);
        }
    }

    void agregarAlCarrito(int id, String nombre, double precio, ImageIcon imagen) {

    for (Object[] item : carrito) {

        if ((int) item[0] == id) {

            item[3] = (int) item[3] + 1;

            return;
        }
    }

    carrito.add(new Object[]{
        id,        // 0
        nombre,    // 1
        precio,    // 2
        1,         // 3 cantidad
        imagen     // 4 imagen
    });
}

    void quitarDelCarrito(int id) {

        for (Object[] item : carrito) {

            if ((int) item[0] == id) {

                int cantidad = (int) item[3] - 1;

                if (cantidad <= 0) {

                    carrito.remove(item);

                } else {

                    item[3] = cantidad;
                }

                return;
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("CAFELUI");

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Volver");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(386, 386, 386))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
javax.swing.JFrame v = new javax.swing.JFrame("Menu Consulta");

    v.setContentPane(new MenuConsulta());

    v.setSize(800, 600);

    v.setLocationRelativeTo(null);

    v.setVisible(true);

    // cerrar ventana actual
    java.awt.Window ventana =
        javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana != null) {
        ventana.dispose();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
JFrame v = new JFrame("Carrito");

        v.setContentPane(new Carrito(carrito));

        v.pack();

        v.setLocationRelativeTo(null);

        v.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
               int fila = jTable1.getSelectedRow();

    int columna = jTable1.getSelectedColumn();

    int id = (int) jTable1.getValueAt(fila, 0);

    String nombre = jTable1.getValueAt(fila, 1).toString();

    double precio = (double) jTable1.getValueAt(fila, 2);

    int stock = (int) jTable1.getValueAt(fila, 3);

    int cantidad = (int) jTable1.getValueAt(fila, 5);

    // ➕ SUMAR
    if (columna == 6) {

        if (stock > 0) {

            cantidad++;

            stock--;

            jTable1.setValueAt(cantidad, fila, 5);

            jTable1.setValueAt(stock, fila, 3);

            ImageIcon imagen = (ImageIcon) jTable1.getValueAt(fila, 4);

agregarAlCarrito(id, nombre, precio, imagen);

            actualizarStock(id, stock);

        } else {

            JOptionPane.showMessageDialog(this, "Sin stock");
        }
    }

    // ➖ RESTAR
    if (columna == 7) {

        if (cantidad > 0) {

            cantidad--;

            stock++;

            jTable1.setValueAt(cantidad, fila, 5);

            jTable1.setValueAt(stock, fila, 3);

            quitarDelCarrito(id);

            actualizarStock(id, stock);
            // TODO add your handling code here:
  // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked
}   }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
