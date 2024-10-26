package com.RegIT.RegIT.Backend.EmailCreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AutoReplyEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAutoReplyEmail(String fullname, String email, String sectionType) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        // Load the HTML template from resources
        Path templatePath = Paths.get(new ClassPathResource("EmailTemp/AutoReplyEmailTemp.html").getURI());
        String body = new String(Files.readAllBytes(templatePath));

        // Replace placeholders with actual values
        body = body.replace("{{fullname}}", fullname);
        body =body.replace("{{sectionType}}",sectionType);

        // Set up email content
        helper.setTo(email);
        helper.setSubject(sectionType + " Submission Confirmation");
        helper.setText(body, true); // Set content type as HTML

        // Send the auto-reply email
        mailSender.send(message);
    }
}
