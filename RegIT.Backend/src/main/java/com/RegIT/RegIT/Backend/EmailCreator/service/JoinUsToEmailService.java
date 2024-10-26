package com.RegIT.RegIT.Backend.EmailCreator.service;

import com.RegIT.RegIT.Backend.model.EmailManager;
import com.RegIT.RegIT.Backend.repo.EmailManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoinUsToEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private  EmailManagerRepo emailManagerRepo;

    public void sendApplicationEmail( String subject, String fileName, byte[] cv, String fullname,String mobileNumber, String email,String coverLetter) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Fetch cc and recipients emails from MongoDB
        List<String> ccEmails = emailManagerRepo.findByEmailType("cc").stream()
                .map(EmailManager::getEmailAddress)
                .collect(Collectors.toList());

        List<String> recipientEmails = emailManagerRepo.findByEmailType("recipients").stream()
                .map(EmailManager::getEmailAddress)
                .collect(Collectors.toList());

        // Set up email recipients, CC, and subject
        helper.setTo(recipientEmails.toArray(new String[0]));
        helper.setCc(ccEmails.toArray(new String[0]));
        helper.setSubject(subject);

        // Load and customize HTML template
        String htmlBody = loadHtmlTemplate();
        htmlBody = htmlBody.replace("{{fullname}}", fullname)
                .replace("{{mobileNumber}}", mobileNumber)
                .replace("{{email}}", email)
                .replace("{{projectDescription}}", coverLetter);

        // Set the email body and content type as HTML
        helper.setText(htmlBody, true);

        // Attach the CV
        helper.addAttachment(fileName, new ByteArrayResource(cv));

        mailSender.send(message);
    }
    // Method to load the HTML template from resources without using URI
    private String loadHtmlTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("EmailTemp/joinUsTemp.html");
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return new String(bytes, StandardCharsets.UTF_8);
    }

}


