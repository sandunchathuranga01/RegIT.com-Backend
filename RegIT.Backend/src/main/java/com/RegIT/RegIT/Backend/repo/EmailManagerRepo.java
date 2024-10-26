package com.RegIT.RegIT.Backend.repo;

import com.RegIT.RegIT.Backend.model.EmailManager;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailManagerRepo extends MongoRepository<EmailManager, String> {

    List<EmailManager> findByEmailType(String emailType);
}
