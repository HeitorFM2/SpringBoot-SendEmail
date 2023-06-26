package com.sendEmail.resource;

import com.sendEmail.model.EmailDetails;
import com.sendEmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EmailResource {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendMail(@RequestBody EmailDetails details){
        return emailService.sendMail(details);
    }

}
