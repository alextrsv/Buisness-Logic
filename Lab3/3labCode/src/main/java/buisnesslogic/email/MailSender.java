package buisnesslogic.email;

import buisnesslogic.model.entity.Course;
import buisnesslogic.exceptions.SendMessageExeption;

import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    static String toAdr;
    static String messageText;
    static private String fromAdr = "sasha.tara2000@yandex.ru";

    public MailSender() {}


    public static void makeSend(UserMessage userMessage) throws SendMessageExeption {
        toAdr = userMessage.getToAddr();
        messageText = userMessage.getMessageText();
        try {
            send();
        }catch (SendMessageExeption exception){
            exception.setMessage(userMessage.getErrorText());
            throw exception;
        }
    }

    public static void send() throws SendMessageExeption {
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.yandex.ru");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", 465);
        SMTPAuthenticator smtpAuthenticator = new SMTPAuthenticator();
        Session s = Session.getDefaultInstance(p, smtpAuthenticator);

        try {
            Message mess = new MimeMessage(s);
            mess.setFrom(new InternetAddress(fromAdr));
            mess.setRecipient(RecipientType.TO, new InternetAddress(toAdr));
            mess.setSubject(messageText);
            mess.setText(messageText);
//            System.out.println("Now sending...");
            Transport.send(mess);

        } catch (Exception ex) {
            System.err.println("ОШИБОЧКА");
            ex.printStackTrace();
            throw new SendMessageExeption();
        }

    }
}
