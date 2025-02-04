package com.org.java4.utils;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mailer {
    public static void sendEmail(String from, String to, String subject, String body) {
        // Thông số kết nối GMail
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        // Đăng nhập GMail
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "";
                String password = "";
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Tạo mail
            MimeMessage mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(from));
            mail.setRecipients(MimeMessage.RecipientType.TO, to);
            mail.setSubject(subject, "utf-8");
            mail.setText(body, "utf-8", "html");
            mail.setReplyTo(mail.getFrom());
            // Gửi mail
            Transport.send(mail);
//            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return -1;
    }
}
