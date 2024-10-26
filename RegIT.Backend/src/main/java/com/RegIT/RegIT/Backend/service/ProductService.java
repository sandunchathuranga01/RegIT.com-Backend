package com.RegIT.RegIT.Backend.service;


import com.RegIT.RegIT.Backend.model.Product;
import com.RegIT.RegIT.Backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(String id, Product productDetails) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setProductID(productDetails.getProductID());
            existingProduct.setProductFolderNumber(productDetails.getProductFolderNumber());
            existingProduct.setClientID(productDetails.getClientID());
            existingProduct.setProductName(productDetails.getProductName());
            existingProduct.setProductCost(productDetails.getProductCost());
            existingProduct.setProductStatus(productDetails.getProductStatus());
            existingProduct.setProductEndDate(productDetails.getProductEndDate());
            existingProduct.setProductStartDate(productDetails.getProductStartDate());
            existingProduct.setProductDescription(productDetails.getProductDescription());
            return productRepo.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}
