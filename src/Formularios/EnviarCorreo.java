package Formularios;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo {

    public static void enviarTicket(String correoDestino, String rutaPDF) {

        final String correoEmisor = "luigi64mdz@gmail.com";
        final String password = "fimp faif fjrb oegy";

        try {

            Properties props = new Properties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(
                    props,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            correoEmisor,
                            password
                    );
                }
            });

            Message mensaje = new MimeMessage(session);

            mensaje.setFrom(new InternetAddress(correoEmisor));
            mensaje.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correoDestino)
            );

            mensaje.setSubject("Ticket de compra - Cafelui");

            MimeBodyPart texto = new MimeBodyPart();

            texto.setText(
                    "Gracias por comprar en Cafelui.\n\n" +
                    "Adjuntamos tu ticket de compra en PDF."
            );

            MimeBodyPart archivo = new MimeBodyPart();

            FileDataSource source =
                    new FileDataSource(new File(rutaPDF));

            archivo.setDataHandler(new DataHandler(source));
            archivo.setFileName(new File(rutaPDF).getName());

            MimeMultipart multipart = new MimeMultipart();

            multipart.addBodyPart(texto);
            multipart.addBodyPart(archivo);

            mensaje.setContent(multipart);

            Transport.send(mensaje);

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Ticket enviado al correo correctamente"
            );

        } catch (Exception e) {

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Error al enviar correo: " + e
            );
        }
    }
}