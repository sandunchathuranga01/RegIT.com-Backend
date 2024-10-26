package com.RegIT.RegIT.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

@Document(collection = "EmailManager")
public class EmailManager {

    @Id
    private String id;

    @NotBlank(message = "Email type is mandatory")
    @Size(min = 2, max = 50, message = "Email type should be between 2 and 50 characters")
    private String emailType;

    @NotBlank(message = "Email owner name is mandatory")
    @Size(min = 2, max = 100, message = "Email owner name should be between 2 and 100 characters")
    private String emailOwnerName;

    @NotBlank(message = "Position is mandatory")
    @Size(min = 2, max = 50, message = "Position should be between 2 and 50 characters")
    private String position;

    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Email address should be valid")
    private String emailAddress;

    // Constructors, Getters, Setters
    public EmailManager() {}

    public EmailManager(String emailType, String emailOwnerName, String position, String emailAddress) {
        this.emailType = emailType;
        this.emailOwnerName = emailOwnerName;
        this.position = position;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailOwnerName() {
        return emailOwnerName;
    }

    public void setEmailOwnerName(String emailOwnerName) {
        this.emailOwnerName = emailOwnerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
