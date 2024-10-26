package com.RegIT.RegIT.Backend.service;

import com.RegIT.RegIT.Backend.repo.EmailManagerRepo;
import com.RegIT.RegIT.Backend.model.EmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailManagerService {

    @Autowired
    private EmailManagerRepo emailManagerRepo;

    // Create or update an email
    public EmailManager saveOrUpdateEmail(EmailManager emailManager) {
        return emailManagerRepo.save(emailManager);
    }

    // Get all emails
    public List<EmailManager> getAllEmails() {
        return emailManagerRepo.findAll();
    }

    // Get email by ID
    public EmailManager getEmailById(String id) {
        return emailManagerRepo.findById(id).orElse(null);
    }

    // Get emails by email type
    public List<EmailManager> getEmailsByType(String emailType) {
        return emailManagerRepo.findByEmailType(emailType);
    }

    // Delete email by ID
    public void deleteEmailById(String id) {
        emailManagerRepo.deleteById(id);
    }
}
