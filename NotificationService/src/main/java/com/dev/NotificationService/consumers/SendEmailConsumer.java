package com.dev.NotificationService.consumers;

import com.dev.NotificationService.Service.EmailService;
import com.dev.NotificationService.dto.SendEmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.Properties;

@Service
public class SendEmailConsumer {
    private ObjectMapper objectMapper;
    private EmailService emailService;

    public SendEmailConsumer(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "sign_up", groupId = "emailService")
    public void handleSignupEvent(String message) {
        try {
            SendEmailDto sendEmailDto = objectMapper.readValue(message, SendEmailDto.class);
            System.out.println("Notification Received - " + sendEmailDto.getTo());
            String smtpHostServer = "smtp.example.com";
            String emailID = "email_me@example.com";

            Properties props = System.getProperties();

            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sendEmailDto.getFrom(), "tesb swct axou wjex");
                }
            };
            Session session = Session.getInstance(props, auth);

            EmailService.sendEmail(session, sendEmailDto.getTo(), sendEmailDto.getSubject(), sendEmailDto.getBody());
        } catch (Exception e) {
            System.out.println("Something went wrong." + e);
        }
    }
}
