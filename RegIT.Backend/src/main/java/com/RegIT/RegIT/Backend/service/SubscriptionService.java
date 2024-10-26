package com.RegIT.RegIT.Backend.service;


import com.RegIT.RegIT.Backend.model.Subscription;
import com.RegIT.RegIT.Backend.repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepo.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepo.findAll();
    }
}
