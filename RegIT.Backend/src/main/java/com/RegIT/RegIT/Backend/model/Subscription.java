package com.RegIT.RegIT.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "subscriptions")
public class Subscription {

    @Id
    private String id;

    @NotEmpty(message = "Email Address is required")
    @Email(message = "Email should be valid")
    private String emailAddress;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
