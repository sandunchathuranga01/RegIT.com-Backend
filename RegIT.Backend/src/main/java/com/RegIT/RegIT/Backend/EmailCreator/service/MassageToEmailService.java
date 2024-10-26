package com.RegIT.RegIT.Backend.EmailCreator.service;

import com.RegIT.RegIT.Backend.EmailCreator.model.MassageToEmailRequest;
import com.RegIT.RegIT.Backend.model.EmailManager;
import com.RegIT.RegIT.Backend.repo.EmailManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class MassageToEmailService {

    private static final Logger logger = LoggerFactory.getLogger(MassageToEmailService.class);

    private String srctionType="Free Consultation Request";

    @Autowired
    private AutoReplyEmailService autoReplyEmailService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailManagerRepo emailManagerRepo;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Method to send email with HTML content
    public void sendEmail(MassageToEmailRequest emailRequest) {
        try {
            // Get recipient and cc emails from MongoDB
            List<String> recipientEmails = getEmailsByType("recipients");
            List<String> ccEmails = getEmailsByType("cc");

            // Validate recipient emails
            List<String> validRecipientEmails = recipientEmails.stream()
                    .filter(this::isValidEmail)
                    .collect(Collectors.toList());

            // Validate CC emails
            List<String> validCcEmails = ccEmails.stream()
                    .filter(this::isValidEmail)
                    .collect(Collectors.toList());

            // If no valid recipient emails, throw an exception
            if (validRecipientEmails.isEmpty()) {
                logger.error("No valid recipient emails found.");
                throw new RuntimeException("No valid recipient emails found.");
            }

            // Load the HTML template and replace placeholders
            String htmlContent = loadEmailTemplate(emailRequest.getName(), emailRequest.getEmail(), emailRequest.getMessage());

            // Create MimeMessage for HTML email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setTo(validRecipientEmails.toArray(new String[0]));

            // Set CC only if there are valid CC emails
            if (!validCcEmails.isEmpty()) {
                helper.setCc(validCcEmails.toArray(new String[0]));
            }

            helper.setSubject("New Message from " + emailRequest.getName());

            // Set the email content as HTML
            helper.setText(htmlContent, true);

            // Send the email
            mailSender.send(mimeMessage);
            autoReplyEmailService.sendAutoReplyEmail(emailRequest.getName(), emailRequest.getEmail(),srctionType);
            logger.info("Email sent successfully to recipients: " + validRecipientEmails);

        } catch (MessagingException | MailException e) {
            // Handle email sending failures
            logger.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            // Handle general failures (e.g., fetching emails from MongoDB)
            logger.error("An error occurred while sending the email", e);
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }

    // Method to get emails by type (recipients or cc)
    private List<String> getEmailsByType(String emailType) {
        try {
            List<EmailManager> emailList = emailManagerRepo.findByEmailType(emailType);
            return emailList.stream()
                    .map(EmailManager::getEmailAddress)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to fetch emails of type: " + emailType, e);
            throw new RuntimeException("Failed to fetch emails of type: " + emailType + ". " + e.getMessage());
        }
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        boolean isValid = EMAIL_PATTERN.matcher(email).matches();
        if (!isValid) {
            logger.warn("Invalid email format detected: " + email);
        }
        return isValid;
    }

    // Load email template and replace placeholders with actual data
    private String loadEmailTemplate(String name, String email, String message) throws Exception {
        // Load HTML file from resources
        ClassPathResource resource = new ClassPathResource("EmailTemp/massageTemp.html");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

        // Replace placeholders with actual data
        content = content.replace("{{name}}", name);
        content = content.replace("{{email}}", email);
        content = content.replace("{{message}}", message);

        return content;
    }
}
