package pl.lepa.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.lepa.crudapp.model.RecoveryMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendJavaMail(RecoveryMessage message,String token) throws MessagingException {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setTo(message.getUserEmail());
        mimeMessageHelper.setSubject(message.getTitle());


        StringBuilder stringBuilderMessage =new StringBuilder();
        stringBuilderMessage.append(message.getMessage());
        stringBuilderMessage.append("<br>Reset Link: <br>");
        stringBuilderMessage.append(message.getBaseUrl());
        stringBuilderMessage.append(token);

        mimeMessageHelper.setText(stringBuilderMessage.toString(),true);

        javaMailSender.send(mimeMessage);
    }
}
