package my.SendMail.com;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class SendMail {

	public static void main(String[] args) throws IOException {



		String msgPath = "src/MailBody";
		ReadFile file = new ReadFile();
		String msgBdy = file.getFileContent(msgPath);
		String mailIdPath = "src/MailList";
		String[] idArr = file.getFileContent(mailIdPath).split("\n");
		System.out.println(msgBdy+""+idArr[2]+"   "+idArr[1]);
//		sendMail(idArr, msgBdy);
		
	}
    public static void sendMail(String[] id,String msg) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        final String username="ina000007@gmail.com";
        final String pwd="";
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pwd);
            }
        });

        try {
        	for(String mailTo:id){
	         Message message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(username));
	         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailTo));
	         message.setSubject("Resume- Software Developer");
	         BodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText(msg);
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	         String filename = "/home/nishant/Downloads/new_Nishant_resume.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart);
	         Transport.send(message);
	         System.out.println("Sent message successfully...."+mailTo);
        	}
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);

        }

    }
}
