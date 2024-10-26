package com.RegIT.RegIT.Backend.EmailCreator.controller;



import com.RegIT.RegIT.Backend.EmailCreator.model.MassageToEmailRequest;
import com.RegIT.RegIT.Backend.EmailCreator.service.MassageToEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;


@RestController
@RequestMapping("/api/massageToEmail")
public class MassageToEmailController {


    @Autowired
    private MassageToEmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@Valid @RequestBody MassageToEmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);



        return ResponseEntity.ok("Email sent successfully");
    }
}

