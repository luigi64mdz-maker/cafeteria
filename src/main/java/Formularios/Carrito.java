package Formularios;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public final class Carrito extends javax.swing.JPanel {

    ArrayList<Object[]> carrito;
    DefaultTableModel modelo;
    
private Image fondo;

    public Carrito(ArrayList<Object[]> carritoRecibido) {

    initComponents();
    
     

    java.net.URL url =
            getClass().getResource("/imagenes/Carrito.png");

    if (url != null) {

        fondo = new ImageIcon(url).getImage();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se encontró Carrito.png"
        );
    }

    

    this.carrito = carritoRecibido;


    cargarCarrito();

    calcularTotal();
}
   
    void cargarCarrito() {

    modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Precio");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Subtotal");

    for (Object[] item : carrito) {

        int id = (int) item[0];
        String nombre = (String) item[1];
        double precio = (double) item[2];
        int cantidad = (int) item[3];

        double subtotal = precio * cantidad;

        modelo.addRow(new Object[]{
            id, nombre, precio, cantidad, subtotal
        });
    }

    jTable1.setModel(modelo);
}
    void calcularTotal() {

    double total = 0;

    for (Object[] item : carrito) {
        double precio = (double) item[2];
        int cantidad = (int) item[3];

        total += precio * cantidad;
    }

    jLabel1.setText("TOTAL: $" + total);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jLabel1.setText("jLabel1");

        jButton1.setText("Comprar");
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
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jButton1)
                .addGap(123, 123, 123)
                .addComponent(jButton2)
                .addContainerGap(278, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(65, 65, 65))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
java.awt.Window ventana = javax.swing.SwingUtilities.getWindowAncestor(this);

    if (ventana instanceof javax.swing.JFrame) {
        javax.swing.JFrame frame = (javax.swing.JFrame) ventana;

        comprasdelconsultor panel = new comprasdelconsultor();

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       if (carrito.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Carrito vacío"
        );

        return;
    }

    // PEDIR CORREO
    String correo = JOptionPane.showInputDialog(
            this,
            "Ingresa el correo para enviar el ticket:"
    );

    if (correo == null || correo.trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "No ingresaste correo"
        );

        return;
    }

    // GENERAR PDF
    String rutaPDF = TicketPDF.generarTicket(carrito);

    // ENVIAR CORREO
    if (rutaPDF != null) {

        EnviarCorreo.enviarTicket(
                correo,
                rutaPDF);
        guardarVenta();

        JOptionPane.showMessageDialog(
                this,
                "Compra realizada ✅"
        );

        carrito.clear();

        cargarCarrito();

        calcularTotal();
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
private void guardarVenta() {
    try {
        java.sql.Connection con = conexion.Conexion.conectar();
        double total = 0;

        for (Object[] item : carrito) {
            double precio = (double) item[2];
            int cantidad = (int) item[3];
            total += precio * cantidad;
        }

        String sqlVenta = "INSERT INTO ventas(fecha, hora, total) VALUES (CURDATE(), CURTIME(), ?)";
        java.sql.PreparedStatement psVenta = con.prepareStatement(
                sqlVenta,
                java.sql.Statement.RETURN_GENERATED_KEYS
        );

        psVenta.setDouble(1, total);
        psVenta.executeUpdate();

        java.sql.ResultSet rs = psVenta.getGeneratedKeys();
        int ventaId = 0;

        if (rs.next()) {
            ventaId = rs.getInt(1);
        }

        // 1. AÑADIMOS 'calificacion' A LA CONSULTA SQL
        String sqlDetalle = """
            INSERT INTO detalle_venta
            (venta_id, producto_id, nombre_producto, cantidad, precio, subtotal, calificacion)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        java.sql.PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);

        for (Object[] item : carrito) {
            int id = (int) item[0];
            String nombre = item[1].toString();
            double precio = (double) item[2];
            int cantidad = (int) item[3];
            double subtotal = precio * cantidad;

            // --- 2. LA MAGIA: PREGUNTAR LA CALIFICACIÓN ---
            int estrellas = 5; // Por defecto le damos 5 por si el cajero se salta el paso
            
            try {
                String input = JOptionPane.showInputDialog(this, 
                    "Del 1 al 5, ¿qué calificación le dio el cliente a: " + nombre + "?");
                
                if (input != null && !input.trim().isEmpty()) {
                    estrellas = Integer.parseInt(input);
                    // Asegurarnos de que no pongan números raros como -2 o 10
                    if (estrellas < 1) estrellas = 1;
                    if (estrellas > 5) estrellas = 5;
                }
            } catch (Exception e) {
                // Si escriben texto en lugar de números, lo ignoramos y se queda en 5
                System.out.println("Entrada inválida, se asigna 5 por defecto.");
            }
            // ----------------------------------------------

            psDetalle.setInt(1, ventaId);
            psDetalle.setInt(2, id);
            psDetalle.setString(3, nombre);
            psDetalle.setInt(4, cantidad);
            psDetalle.setDouble(5, precio);
            psDetalle.setDouble(6, subtotal);
            psDetalle.setInt(7, estrellas); // Enviamos las estrellas a la base de datos

            psDetalle.executeUpdate();
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
                this,
                "Error al guardar venta:\n" + e
        );
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
