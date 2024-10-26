package com.RegIT.RegIT.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;


@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    @NotEmpty(message = "Product ID is required")
    private String ProductID;

    @NotEmpty(message = "NIC ID is required")
    private String NicID;

    @NotEmpty(message = "Country is required")
    private String Country;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
    private String PhoneNumber;

    @Email(message = "Email should be valid")
    private String Email;

    @NotEmpty(message = "Address is required")
    @Size(max = 255, message = "Address should not exceed 255 characters")
    private String Address;

    private String Remark;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getNicID() {
        return NicID;
    }

    public void setNicID(String nicID) {
        NicID = nicID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
