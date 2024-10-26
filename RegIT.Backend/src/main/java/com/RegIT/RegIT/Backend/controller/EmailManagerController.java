package com.RegIT.RegIT.Backend.controller;


import com.RegIT.RegIT.Backend.model.EmailManager;
import com.RegIT.RegIT.Backend.service.EmailManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailManagerController {

    @Autowired
    private EmailManagerService emailManagerService;

    // Create or update an email
    @PostMapping
    public ResponseEntity<EmailManager> createOrUpdateEmail(@Valid @RequestBody EmailManager emailManager) {
        EmailManager savedEmail = emailManagerService.saveOrUpdateEmail(emailManager);
        return ResponseEntity.ok(savedEmail);
    }

    // Get all emails
    @GetMapping
    public List<EmailManager> getAllEmails() {
        return emailManagerService.getAllEmails();
    }

    // Get email by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmailManager> getEmailById(@PathVariable String id) {
        EmailManager email = emailManagerService.getEmailById(id);
        if (email != null) {
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete email by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailById(@PathVariable String id) {
        emailManagerService.deleteEmailById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all 'cc' emails
    @GetMapping("/cc")
    public ResponseEntity<List<EmailManager>> getAllEmailcc() {
        List<EmailManager> emails = emailManagerService.getEmailsByType("cc");
        return ResponseEntity.ok(emails);
    }

    // Get all 'recipients' emails
    @GetMapping("/recipients")
    public ResponseEntity<List<EmailManager>> getAllEmailrecipients() {
        List<EmailManager> emails = emailManagerService.getEmailsByType("recipients");
        return ResponseEntity.ok(emails);
    }
}
