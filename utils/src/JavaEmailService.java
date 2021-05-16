import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmailService {

  public static void main(String[] args) {

    final String username = "from@gmail.com";
    final String password = "frompwd";

    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); //TLS

    Session session = Session.getInstance(prop,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("from@gmail.com"));
      message.setRecipients(
          Message.RecipientType.TO,
          InternetAddress.parse("to@gmail.com")
      );
      message.setSubject("Testing Java Email Sender");
      message.setText("Hello!");

      Transport.send(message);

      System.out.println("Done");

    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
