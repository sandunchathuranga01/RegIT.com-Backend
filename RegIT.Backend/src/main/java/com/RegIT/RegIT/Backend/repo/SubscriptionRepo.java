package com.RegIT.RegIT.Backend.repo;


import com.RegIT.RegIT.Backend.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends MongoRepository<Subscription, String> {
}
