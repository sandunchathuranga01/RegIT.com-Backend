package com.RegIT.RegIT.Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.util.Date;

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotEmpty(message = "Product ID is required")
    private String ProductID;

    @NotEmpty(message = "Product Folder Number is required")
    private String ProductFolderNumber;

    @NotEmpty(message = "Client ID is required")
    private String ClientID;

    @NotEmpty(message = "Product Name is required")
    private String ProductName;

    @NotEmpty(message = "Product Cost is required")
    private String ProductCost;

    @NotEmpty(message = "Product Status is required")
    private String ProductStatus;

    private Date ProductEndDate;
    private Date ProductStartDate;
    private String ProductDescription;

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

    public String getProductFolderNumber() {
        return ProductFolderNumber;
    }

    public void setProductFolderNumber(String productFolderNumber) {
        ProductFolderNumber = productFolderNumber;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCost() {
        return ProductCost;
    }

    public void setProductCost(String productCost) {
        ProductCost = productCost;
    }

    public String getProductStatus() {
        return ProductStatus;
    }

    public void setProductStatus(String productStatus) {
        ProductStatus = productStatus;
    }

    public Date getProductEndDate() {
        return ProductEndDate;
    }

    public void setProductEndDate(Date productEndDate) {
        ProductEndDate = productEndDate;
    }

    public Date getProductStartDate() {
        return ProductStartDate;
    }

    public void setProductStartDate(Date productStartDate) {
        ProductStartDate = productStartDate;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }
}
