package util;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author nhatm
 */
public class EmailUtil {

    final String from = "kholuutruanh123@gmail.com";
    final String password = "cijs zadi xhkk fxov";

    //Send email from ... to ..., if type is signup then send sign up mail, type is forgotpass then send password reset mail
    public void sendEmail(String to, String type, String generatedValue) {
        String emailContent = "";
        PasswordUtil passUtil = new PasswordUtil();
        //Properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        };
        //Session
        Session session = Session.getInstance(props, auth);

        //Send email
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            //Sender
            msg.setFrom(from);

            //Receiver
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            if (type.equals("forgotpass")) {
                //Genpass
                //generatedValue = passUtil.generatePassword(); 
                //Subject
                msg.setSubject("Request to reset password ");

                //Content
                emailContent = "Hello user,\n\n"
                        + "** This is an automated message -- please do not reply as you will not receive a response. **\n\n"
                        + "This message is in response to your request to reset your account password. Please click the link below and follow the instructions to change your password.\n\n"
                        + "Your password is: " + generatedValue + "\n\n"
                        + "Note: Your temporary password is only available in 3 mins from the momment this email is sent!!\n\n"
                        + "http://localhost:8080/SWP391_M2_BL5_G3/Login.jsp \n\n"
                        + "Thank you.";
            } 
            msg.setText(emailContent, "UTF-8");
            //Send Email
            Transport.send(msg);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + to);
            e.printStackTrace();
        }
    }

}