package com.RegIT.RegIT.Backend.EmailCreator.controller;

import com.RegIT.RegIT.Backend.EmailCreator.service.AutoReplyEmailService;
import com.RegIT.RegIT.Backend.EmailCreator.service.JoinUsToEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import jakarta.mail.MessagingException;

import java.io.IOException;

@RestController
@RequestMapping("/api/JoinUsToEmail")
public class JoinUsToEmailController {

    private String srctionType="Join Us Request";

    @Autowired
    private AutoReplyEmailService autoReplyEmailService;

    @Autowired
    private JoinUsToEmailService joinUsToEmailService;

    @PostMapping(value = "/submit", consumes = "multipart/form-data")
    public String submitApplication(@RequestParam("fullname") String fullname,
                                    @RequestParam("mobileNumber") String mobileNumber,
                                    @RequestParam("email") String email,
                                    @RequestParam("coverLetter") String coverLetter,
                                    @RequestParam("cv") MultipartFile cv) {

        // Validate projectDescription size (approx. 600 words, up to 3600 characters)
        if (coverLetter.length() > 3600) {
            return "coverLetter exceeds the allowed limit of 600 words.";
        }

        try {
            String subject = "New Job Application from " + fullname;

            // Call the service to send the email with the CV attached
            joinUsToEmailService.sendApplicationEmail(subject,
                    cv.getOriginalFilename(),
                    cv.getBytes(),
                    fullname, mobileNumber, email, coverLetter);
            // Send auto-reply email to the user
            autoReplyEmailService.sendAutoReplyEmail(fullname, email,srctionType);

            return "Application submitted successfully!";
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "Failed to submit the application.";
        }
    }
}

