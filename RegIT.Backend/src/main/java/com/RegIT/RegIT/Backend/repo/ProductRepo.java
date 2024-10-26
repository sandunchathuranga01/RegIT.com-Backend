package com.RegIT.RegIT.Backend.repo;


import com.RegIT.RegIT.Backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
}
