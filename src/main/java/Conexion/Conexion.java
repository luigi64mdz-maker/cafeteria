package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    public static Connection conectar() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

           con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/Cafelui?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "root",
    "1234"
);

            System.out.println("Conexión exitosa");

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el driver MySQL:\n\n" + e);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a MySQL:\n" + e);
        }

        return con;
    }
}