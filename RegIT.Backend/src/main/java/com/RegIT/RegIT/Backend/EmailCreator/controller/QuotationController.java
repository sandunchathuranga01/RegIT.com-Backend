package com.RegIT.RegIT.Backend.EmailCreator.controller;

import com.RegIT.RegIT.Backend.EmailCreator.service.QuotationEmailService;
import com.RegIT.RegIT.Backend.EmailCreator.service.AutoReplyEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;

import java.io.IOException;

@RestController
@RequestMapping("/api/Quotation")
public class QuotationController {
    private String srctionType="Quotation Request";

    @Autowired
    private AutoReplyEmailService autoReplyEmailService;

    @Autowired
    private QuotationEmailService quotationEmailService;

    @PostMapping(value = "/submit", consumes = "multipart/form-data")
    public String submitQuotation(@RequestParam("fullname") String fullname,
                                  @RequestParam("mobileNumber") String mobileNumber,
                                  @RequestParam("email") String email,
                                  @RequestParam("companyName") String companyName,
                                  @RequestParam("projectDescription") String projectDescription,
                                  @RequestParam("quotationDocument") MultipartFile quotationDocument) {

        // Validate projectDescription size (approx. 600 words, up to 3600 characters)
        if (projectDescription.length() > 3600) {
            return "Project description exceeds the allowed limit of 600 words.";
        }

        try {
            String subject = "New Quotation Submission from " + fullname;

            // Call the service to send the email with the HTML template and CC included
            quotationEmailService.sendQuotationEmail(subject,
                    quotationDocument.getOriginalFilename(),
                    quotationDocument.getBytes(),
                    fullname, mobileNumber, email, companyName, projectDescription);
            // Send auto-reply email to the user
            autoReplyEmailService.sendAutoReplyEmail(fullname, email,srctionType);

            return "Quotation submitted successfully!";


        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "Failed to submit the quotation.";
        }
    }
}
