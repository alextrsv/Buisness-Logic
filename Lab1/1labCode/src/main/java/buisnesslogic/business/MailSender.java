package buisnesslogic.business;

import buisnesslogic.entity.Course;
import buisnesslogic.exceptions.SendMessageExeption;

import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    static String fromAdr;
    static String toAdr;
    static String message;
    static Course course;

    public MailSender() {
    }


    public static void makeSend(String toAdrInit, Course course) throws SendMessageExeption {
        toAdr = toAdrInit;
        fromAdr = "sasha.tara2000@yandex.ru";
        message = "Здравствуйте! Вы записаны на курс " + course.toString();
        try {
            send();
        }catch (SendMessageExeption exception){
            exception.setMessage("sending email trouble\nUser has not been entrolled to course" + course.toString());
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
            mess.setSubject(message);
            mess.setText(message);
            System.out.println("Now sending...");
            Transport.send(mess);
            System.out.println("sended");
        } catch (Exception ex) {
            System.err.println("ОШИБОЧКА");
            ex.printStackTrace();
            throw new SendMessageExeption();
        }

    }
}
