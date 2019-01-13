package com.tod.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.ChallengeRequest;

@Component
@Repository
public interface ChallengeRequestRepository extends MongoRepository<ChallengeRequest, String>{

	ChallengeRequest findById(String id);
}
