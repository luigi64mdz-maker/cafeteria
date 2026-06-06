import javax.swing.JFrame;
import Formularios.Login;

public class Principal {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Login");

        Login panel = new Login();

        ventana.setContentPane(panel); 
        ventana.pack();                
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}