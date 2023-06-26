package com.sendEmail.service;

import com.sendEmail.dto.ResultDto;
import com.sendEmail.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")   
    private String sender;

    public ResponseEntity<?> sendMail(EmailDetails details){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        try {
            //Quem manda
            mailMessage.setFrom(sender);
            //Para quem
            mailMessage.setTo("heitorfm.dev@gmail.com");
            //Mensagem
            mailMessage.setText("Mail: " + details.getSender() + "\n Mensagem: " + details.getMessageBody());
            //Assunto
            mailMessage.setSubject(details.getNameSender());

            javaMailSender.send(mailMessage);

            ResultDto result = new ResultDto();

            result.setMessage("Email enviado com sucesso");
            result.setSuccess(true);

            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}
