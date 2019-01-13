package com.tod.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tod.model.Challenge;

@Component
@Repository
public interface ChallengeRepository extends MongoRepository<Challenge, String>{

	Challenge findById(String id);
}
